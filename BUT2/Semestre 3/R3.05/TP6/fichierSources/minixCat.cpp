#include <iostream>
#include <fstream>
#include <string>

int main(int argc, char* argv[]) {
    if (argc < 2) {
        std::cerr << "Usage: " << argv[0] << " <fichier1> [fichier2 ...]" << std::endl;
        return 1;
    }

    for (int i = 1; i < argc; ++i) {
        std::ifstream file(argv[i]);

        if (!file) {
            std::cerr << "Erreur: Impossible d'ouvrir le fichier " << argv[i] << std::endl;
            continue;
        }

        std::string line;
        while (std::getline(file, line)) {
            std::cout << line << std::endl;
        }

        file.close();
    }

    return 0;
}
