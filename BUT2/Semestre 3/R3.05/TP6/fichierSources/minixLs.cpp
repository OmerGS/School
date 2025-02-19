#include <iostream>
#include <iomanip>
#include <sys/stat.h>
#include <dirent.h>
#include <cstring>
#include <ctime>
#include <pwd.h>
#include <grp.h>

// On affiche les permissions
void printPermissions(mode_t mode) {
    static const char* permissions = "rwxrwxrwx";
    std::cout << ((S_ISDIR(mode)) ? 'd' : '-');
    for (int i = 0; i < 9; ++i) {
        std::cout << ((mode & (1 << (8 - i))) ? permissions[i] : '-');
    }
}

// On affiche les details des fichiers
void printFileDetails(const struct stat& fileStat, const char* filename) {
    printPermissions(fileStat.st_mode);
    std::cout << " " << fileStat.st_nlink;

    struct passwd* pw = getpwuid(fileStat.st_uid);
    struct group* gr = getgrgid(fileStat.st_gid);
    std::cout << " " << (pw ? pw->pw_name : "unknown");
    std::cout << " " << (gr ? gr->gr_name : "unknown");

    std::cout << " " << std::setw(8) << fileStat.st_size;

    char timeBuf[80];
    struct tm* timeinfo = localtime(&fileStat.st_mtime);
    strftime(timeBuf, sizeof(timeBuf), "%b %d %H:%M", timeinfo);
    std::cout << " " << timeBuf;

    std::cout << " " << filename << std::endl;
}

int main(int argc, char* argv[]) {
    const char* path = (argc == 2) ? argv[1] : ".";
    DIR* directory = opendir(path);

    // Si il n'y a pas de repertoire alors on retourne une erreur.
    if (!directory) {
        std::cerr << "Impossible d'ouvrir le répertoire " << path << std::endl;
        return 1;
    }

    // On affiche pas les fichier commençant par . ou ..
    struct dirent* entry;
    while ((entry = readdir(directory))) {
        if (entry->d_name[0] == '.' && (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)) {
            continue; 
        }

        struct stat fileStat;
        std::string fullPath = std::string(path) + "/" + entry->d_name;

        if (stat(fullPath.c_str(), &fileStat) == 0) {
            printFileDetails(fileStat, entry->d_name);
        } else {
            std::cerr << "Erreur: Impossible de lire les informations de " << entry->d_name << std::endl;
        }
    }

    closedir(directory);
    return 0;
}
