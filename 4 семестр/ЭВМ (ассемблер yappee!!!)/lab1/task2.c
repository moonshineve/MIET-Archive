#include <stdio.h>
#include <string.h>

// # превращает аргумент макроса в строковый литерал
#define PRINT_TYPE_SIZE(type) printf("%-23s%d байт\n", #type, sizeof(type))

char* get_os_name();
char* get_compiler_name();
int get_program_word_size();  // режим, в котором скомпилирован исполняемый файл (разрядность сборки)
char* get_cpu_architecture();


void task2(){
    puts("Задание 2");

    char* ptr_os_name = get_os_name();
    printf("Операционная система:   %s x64\n", ptr_os_name);

    char* prt_compiler_name = get_compiler_name();
    printf("Компилятор:             %s\n", prt_compiler_name);
    sizeof(12);
    printf("Разрядность сборки:     x%d\n", get_program_word_size());

    char* prt_cpu_architecture = get_cpu_architecture();
    printf("Архитектура процессора: %s\n", prt_cpu_architecture);

    printf("Назначение платформы:   Платформа общего назначения\n\n");

    printf("Тип данных             Размер в байтах\n");
    PRINT_TYPE_SIZE(void*);
    PRINT_TYPE_SIZE(char);
    PRINT_TYPE_SIZE(signed char);
    PRINT_TYPE_SIZE(unsigned char);
    PRINT_TYPE_SIZE(char*);
    PRINT_TYPE_SIZE(wchar_t);
    PRINT_TYPE_SIZE(wchar_t*);
    PRINT_TYPE_SIZE(short);
    PRINT_TYPE_SIZE(unsigned short);
    PRINT_TYPE_SIZE(short*);
    PRINT_TYPE_SIZE(int);
    PRINT_TYPE_SIZE(unsigned int);
    PRINT_TYPE_SIZE(int*);
    PRINT_TYPE_SIZE(long);
    PRINT_TYPE_SIZE(unsigned long);
    PRINT_TYPE_SIZE(long long);
    PRINT_TYPE_SIZE(unsigned long long);
    PRINT_TYPE_SIZE(float);
    PRINT_TYPE_SIZE(double);
    PRINT_TYPE_SIZE(long double);
    PRINT_TYPE_SIZE(double*);
    PRINT_TYPE_SIZE(size_t);
    PRINT_TYPE_SIZE(ptrdiff_t);

    free(ptr_os_name);
    free(prt_compiler_name);
    free(prt_cpu_architecture);
}

char* get_os_name() {
    char *ptr = (char*)malloc(sizeof(char)*19);

    #if defined(_WIN32) || defined(_WIN64)
        strcpy(ptr, "Windows");
    #elif defined(TARGET_OS_MAC)
        strcpy(ptr, "macOS");
    #elif defined(__linux__)
        strcpy(ptr, "Linux");
    #else
        strcpy(ptr, "Запрещенная для ЛР");
    #endif

    return ptr;
}

char* get_compiler_name() {
    char *ptr = (char*)malloc(sizeof(char)*20);

    #if defined(__MINGW32__) || defined(__MINGW64__)
        sprintf(ptr, "MinGW %d.%d.%d", __GNUC__, __GNUC_MINOR__, __GNUC_PATCHLEVEL__);

    #elif defined(__GNUC__)
        sprintf(ptr, "GCC %d.%d.%d", __GNUC__, __GNUC_MINOR__, __GNUC_PATCHLEVEL__);

    #else
        strcpy(ptr, "Запрещеннй для ЛР");

    #endif

    return ptr;
}

int get_program_word_size() {
    return sizeof(char*) * 8;
}

char* get_cpu_architecture() {
    char *ptr = (char*)malloc(sizeof(char)*16);

    #if defined(__x86_64__) || defined(_M_X64)
        strcpy(ptr, "x86-64 (64-bit)");
    #elif defined(__i386__) || defined(_M_IX86)
        strcpy(ptr, "x86 (32-bit)");
    #else
        strcpy(ptr, "Неизвестная");
    #endif

    return ptr;
}

