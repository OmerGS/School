#include <iostream>
#include <sstream>
#include <errno.h>
#include <iomanip>      
#include <string>
#include <string.h>
#include <vector>
#include <ctime>
#include <linux/byteorder/little_endian.h>
#include <unistd.h>
#include <time.h>
#include <cstdint>

#define INODE_SIZE sizeof(struct minix_inode)
#define INODE_TABLE_BASE_ADDRESS 0xfd414200a0000000



struct minix_inode {
  __u16 i_mode ;
  __u16 i_uid ;
  __u32 i_size ;
  __u32 i_time ;
  __u8 i_gid ;
  __u8 i_nlinks ;
  __u16 i_zone [9];
};


int 
ua2adr(int ua) {
    return ua * sysconf(_SC_PAGESIZE);
}

int i2adr(int i) {
    return INODE_TABLE_BASE_ADDRESS + i * INODE_SIZE;
}

char prop2type(int i){ 
    printf("i=%d",i);
    
    if(i >> 12 == 4) return 'd';
    if(i >> 12 == 8) return '-';
    return 1;
}

std::string idate2sdate(__u32 i_time) {
    time_t raw_time = static_cast<time_t>(i_time);
    struct tm* time_info = localtime(&raw_time);
    std::string date_str = asctime(time_info);
    if (!date_str.empty()) {
        date_str.pop_back();
    }

    return date_str;
}

void readBlock(FILE *file, __u16 ua, __u16 zone[]) {
    long offset = static_cast<long>(ua) * sizeof(__u16);
    
    if (fseek(file, offset, SEEK_SET) != 0) {
        perror("Erreur de positionnement dans le fichier");
        return;
    }
    
    size_t num_elements = sizeof(zone) / sizeof(zone[0]);
    
    size_t items_read = fread(zone, sizeof(__u16), num_elements, file);
    if (items_read != num_elements) {
        perror("Erreur de lecture du bloc d'extension de la table d'allocation");
    }
}

std::string prop2RWX(int i){
    
    unsigned int intenger = i & 0b0111;
    std::string result = "";
    if((intenger & 0b0100) == 0b0100) result += 'r';
    else result += '-';
    if(intenger & 0b0010 == 0b0010) result += 'w';
    else result += '-';
    if(intenger & 0b0001 == 0b0001)result += 'x';
    else result += '-';

    return result;
}

std::string prop2tUGO (int i) {
    std::string tuf = "";
    tuf += prop2type(i);
    for(int j = 0; j < 3; j++){
        tuf += prop2RWX(i);
    }
    return tuf;
}

//oct.   9 14:44
std::string idate2sdate(__u32 i_time) {
    time_t time = static_cast<time_t>(i_time);
    struct tm* timeinfo = localtime(&time);

    int day = timeinfo->tm_mday;
    int month = timeinfo->tm_mon; 
    int year = timeinfo->tm_year + 1900; 
    int hour = timeinfo->tm_hour;
    int minute = timeinfo->tm_min;

    const char* mois[] = {
        "jan.", "févr.", "mars", "avr.", "mai", "juin",
        "juil.", "août", "sept.", "oct.", "nov.", "déc."
    };

    std::ostringstream oss;
    oss << mois[month] << " " << day << " " << hour << ":" << (minute < 10 ? "0" : "") << minute;

    return oss.str();

}

struct minix_inode {
    uint16_t i_mode;      
    uint16_t i_link_count; 
    uint32_t i_size;     
    uint32_t i_time;     
    uint32_t i_block[10];
};

void readInode(FILE *file, int id, minix_inode &inode) {
    long offset = static_cast<long>(id) * sizeof(minix_inode);
    
    if (fseek(file, offset, SEEK_SET) != 0) {
        perror("Erreur de positionnement dans le fichier");
        return;
    }
    
    size_t bytes_read = fread(&inode, sizeof(minix_inode), 1, file);
    if (bytes_read != 1) {
        perror("Erreur de lecture de l'inode");
    }
}

struct minix_dir_entry {
    uint32_t inode;     
    char name[60];  
};

void readDir(FILE *file, std::vector<minix_dir_entry> &entries, const minix_inode &inode) {
    if ((inode.i_mode & 0xF000) != 0x4000) { 
        printf("Ce n'est pas un répertoire.\n");
        return;
    }

    long offset = static_cast<long>(inode.i_block[0]) * 1024; 
    
    if (fseek(file, offset, SEEK_SET) != 0) {
        perror("Erreur de positionnement dans le fichier");
        return;
    }

    size_t entry_size = sizeof(minix_dir_entry);
    minix_dir_entry entry;

    while (fread(&entry, entry_size, 1, file) == 1) {
        if (entry.inode == 0) {
            break;
        }
        entries.push_back(entry);
    }
}

int findInode(FILE *file, const minix_inode &inode, const std::string &filename) {
    if ((inode.i_mode & 0xF000) != 0x4000) {  
        printf("Ce n'est pas un répertoire.\n");
        return -1;  
    }

    long offset = static_cast<long>(inode.i_block[0]) * 1024;
    if (fseek(file, offset, SEEK_SET) != 0) {
        perror("Erreur de positionnement dans le fichier");
        return -1;
    }

    size_t entry_size = sizeof(minix_dir_entry);
    minix_dir_entry entry;

    while (fread(&entry, entry_size, 1, file) == 1) {
        if (entry.inode == 0) {
            break;
        }

        if (std::strcmp(entry.name, filename.c_str()) == 0) {
            return entry.inode;
        }
    }

    return -1;
}

int 
main(){
    std::string result = idate2sdate(1728479996);
    std::cout << "time=";
    std::cout << result ;
    std::cout << "\n\n";
    unsigned int test = i2adr(0);
    printf("test=%x\n",test);
}