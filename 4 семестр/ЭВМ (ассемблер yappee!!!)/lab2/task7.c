#include "header.h"
#include <string.h>

#define PRINT_INT(value)    printf("%+-11d- ", *(int*)value)
#define PRINT_FLOAT(value)  printf("%+-11f- ", *(float*)value)
#define PRINT_CHAR(value)   printf("%-s - ", (char*)value)
#define PRINT_WCHAR_T(value) wprintf(L"%-11ls - ", (wchar_t*)value)

void task7(){

    int int_x = 5;
    int int_y = -5;
    int int_a = 1;
    int int_b = 2;
    int int_c = 12345689;
    int int_d = 123456891;

    void* ptr_int_x = &int_x;
    void* ptr_int_y = &int_y;
    void* ptr_int_a = &int_a;
    void* ptr_int_b = &int_b;
    void* ptr_int_c = &int_c;
    void* ptr_int_d = &int_d;

    float float_x = 5;
    void* ptr_float_x = &float_x;

    void* char_str1    =  "abc012";
    void* char_str2    =  "абв012"; 
    void* wchar_t_str1 = L"abc012";
    void* wchar_t_str2 = L"абв012"; 

    puts("Int values:");
    PRINT_INT(ptr_int_x); printDump(ptr_int_x, 4);
    PRINT_INT(ptr_int_y); printDump(ptr_int_y, 4);
    PRINT_INT(ptr_int_a); printDump(ptr_int_a, 4);
    PRINT_INT(ptr_int_b); printDump(ptr_int_b, 4);
    PRINT_INT(ptr_int_c); printDump(ptr_int_c, 4);
    PRINT_INT(ptr_int_d); printDump(ptr_int_d, 4);

    puts("\nFloat value:");
    PRINT_FLOAT(ptr_float_x); printDump(ptr_float_x, 4);

    puts("\nChar strings:");
    PRINT_CHAR(char_str1); printDump(char_str1, strlen(char_str1));
    PRINT_CHAR(char_str2); printDump(char_str2, strlen(char_str2));

    puts("\nwchar_t strings:");
    PRINT_CHAR(char_str1); printDump(wchar_t_str1, wcslen(wchar_t_str1) * sizeof(wchar_t));
    PRINT_CHAR(char_str2); printDump(wchar_t_str2, wcslen(wchar_t_str2) * sizeof(wchar_t));
}

// 'i': int; 'f': float;
void printDump(void *p, size_t N){

    unsigned char* char_ptr = (unsigned char*)p;
    
    for (int i=0; i<N; i++){
        printf("%02hhX ", *(char_ptr+i));
    }
    puts("");
}