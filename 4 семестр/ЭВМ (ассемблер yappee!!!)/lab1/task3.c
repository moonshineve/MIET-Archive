#include <stdio.h>


void init_array(void* arr, int size, size_t elem_size, const void* value);
void print_binary_recursive(unsigned int n);
void print_int_array(void* arr, int size, size_t elem_size, const char* name);
void print_fp_array(void* arr, int size, size_t elem_size, const char* name);


void task3(){
    printf("Задание № 3\n");

    const int N = 5;

    unsigned char      x_char   = 0xA7; // unsigned потому что 0xA7 = 167 > 128
    unsigned short     x_short  = 0xC0DE;
    unsigned int       x_int    = 0xDEADBEEF;
    unsigned long long x_ll     = 0x000D15A550C1A7ED;
    float  x_float  = -3.0f/7.0f;
    double x_double = -3.0/7.0;

    unsigned char      Mb[N];
    unsigned short     Ms[N];
    unsigned int       Ml[N];
    unsigned long long Mq[N];
    float              Mfs[N];
    double             Mfl[N];

    init_array(Mb,  N, sizeof(unsigned char),      &x_char);
    init_array(Ms,  N, sizeof(unsigned short),     &x_short);
    init_array(Ml,  N, sizeof(unsigned int),       &x_int);
    init_array(Mq,  N, sizeof(unsigned long long), &x_ll);
    init_array(Mfs, N, sizeof(float),              &x_float);
    init_array(Mfl, N, sizeof(double),             &x_double);

//    print_int_array(Mb, N, sizeof(unsigned char),      "Mb");
//    print_int_array(Ms, N, sizeof(unsigned short),     "Ms");
//    print_int_array(Ml, N, sizeof(unsigned int),       "Ml");
//    print_int_array(Mq, N, sizeof(unsigned long long), "Mq");

    print_fp_array(Mfs, N, sizeof(float), "Mfs");
    print_fp_array(Mfl, N, sizeof(double), "Mfl");

}


void init_array(void* arr, int size, size_t elem_size, const void* value) {

    char* byte_arr = (char*)arr; // привод к char* для побайтовой адресации

    for (int i = 0; i < size; i++) {
        memcpy(byte_arr + i * elem_size, value, elem_size);
    }
}

void print_binary_recursive(unsigned int n){
    if (n > 1)
        print_binary_recursive(n / 2);
    printf("%d", n % 2);
}

void print_int_array(void* arr, int size, size_t elem_size, const char* name) {
    printf("МАССИВ %s\n", name);



    printf("В 16-ричном виде:\n");
    for (int i = 0; i < size; i++)
    {
        switch (elem_size)
        {
            case sizeof(unsigned char):
                printf("0x%02X ", ((unsigned char*)arr)[i]);
                break;

            case sizeof(unsigned short):
                printf("0x%04X ", ((unsigned short*)arr)[i]);
                break;

            case sizeof(unsigned int):
                printf("0x%08X ", ((unsigned int*)arr)[i]);
                break;

            case sizeof(unsigned long long):
                printf("0x%016llX ", ((unsigned long long*)arr)[i]);
                break;

            default:
                printf("[unsupported]");
                return;
        }
    }
    printf("\n\n");

    printf("В 2-ичном виде:\n");
    for (int i = 0; i < size; i++)
    {
        switch (elem_size)
        {
            case sizeof(unsigned char):
            {
                unsigned char val = ((unsigned char*)arr)[i];
                printf("0b");
                for (int bit = 7; bit >= 0; bit--)
                    printf("%d", (val >> bit) & 1);
                printf(" ");
                break;
            }

            case sizeof(unsigned short):
            {
                unsigned short val = ((unsigned short*)arr)[i];
                printf("0b");
                for (int bit = 15; bit >= 0; bit--)
                    printf("%d", (val >> bit) & 1);
                printf(" ");
                break;
            }

            case sizeof(unsigned int):
            {
                unsigned int val = ((unsigned int*)arr)[i];
                printf("0b");
                for (int bit = 31; bit >= 0; bit--)
                    printf("%d", (val >> bit) & 1);
                printf(" ");
                break;
            }

            case sizeof(unsigned long long):
            {
                unsigned long long val = ((unsigned long long*)arr)[i];
                printf("0b");
                for (int bit = 63; bit >= 0; bit--)
                    printf("%d", (val >> bit) & 1);
                printf(" ");
                break;
            }
        }
    }
    printf("\n\n");

    printf("В 10-ричном беззнаковом виде:\n");
    for (int i = 0; i < size; i++)
    {
        switch (elem_size)
        {
            case sizeof(unsigned char):
                printf("%u ", ((unsigned char*)arr)[i]);
                break;

            case sizeof(unsigned short):
                printf("%u ", ((unsigned short*)arr)[i]);
                break;

            case sizeof(unsigned int):
                printf("%u ", ((unsigned int*)arr)[i]);
                break;

            case sizeof(unsigned long long):
                printf("%llu ", ((unsigned long long*)arr)[i]);
                break;
        }
    }
    printf("\n\n");

    printf("В 10-ричном знаковом виде:\n");
    for (int i = 0; i < size; i++)
    {
        switch (elem_size)
        {
            case sizeof(unsigned char):
                printf("%d ", (signed char)((unsigned char*)arr)[i]);
                break;

            case sizeof(unsigned short):
                printf("%d ", (signed short)((unsigned short*)arr)[i]);
                break;

            case sizeof(unsigned int):
                printf("%d ", (signed int)((unsigned int*)arr)[i]);
                break;

            case sizeof(unsigned long long):
                printf("%lld ", (signed long long)((unsigned long long*)arr)[i]);
                break;
        }
    }
    printf("\n\n");
}

void print_fp_array(void* arr, int size, size_t elem_size, const char* name){
    printf("МАССИВ %s\n", name);

    int is_float = (elem_size == sizeof(float));

    printf("В 16-ричном экспоненциальном виде:\n");
    for (int i = 0; i < size; i++)
    {
        if (is_float)
            printf("%A  ", ((float*)arr)[i]);
        else
            printf("%A  ", ((double*)arr)[i]);
    }
    printf("\n\n");


    printf("В 10-тичном экспоненциальном виде:\n");
    for (int i = 0; i < size; i++)
    {
        if (is_float)
            printf("%e  ", ((float*)arr)[i]);
        else
            printf("%e  ", ((double*)arr)[i]);
    }
    printf("\n\n");


    printf("С десятичной запятой:\n");
    for (int i = 0; i < size; i++)
    {
        if (is_float)
            printf("%f  ", ((float*)arr)[i]);
        else
            printf("%.15f  ", ((double*)arr)[i]);
    }
    printf("\n\n");
}
