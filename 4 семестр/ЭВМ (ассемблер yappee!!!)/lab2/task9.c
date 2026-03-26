#include "header.h"

void task9(){
    unsigned short us = 52;
    void* ptr = &us;
    ab16(ptr);
}

void ab16(void *p){
    unsigned short us = *(unsigned short*)p;
    printf("Беззнаковое умножение на 2:         %hu\n", us*2);
    printf("Беззнаковый сдвиг вправо на 1 бит:  %hu\n", us<<1);

    printf("Беззнаковое деление на 2:           %hu\n", us/2);
    printf("Беззнаковый сдвиг влево на 1 бит:   %hu\n", us>>1);

    printf("Остаток беззнакового деления на 16: %hu\n", us%16);
    printf("x & 15:                             %hu\n", us&15);

    printf("Округление вниз до кратного 16:     %hu\n", (us/16)*16);
    printf("x & −16:                            %hu\n", us&-16);



    short ss = *(unsigned short*)p;
    printf("Ззнаковое умножение на 2:           %hd\n", ss*2);
    printf("Ззнаковый сдвиг вправо на 1 бит:    %hd\n", ss<<1);

    printf("Знаковое деление на 2:              %hd\n", ss/2);
    printf("Ззнаковый сдвиг влево на 1 бит:     %hd\n", ss>>1);
}