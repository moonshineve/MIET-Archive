#include "header.h"

void task5(){
    unsigned int min_unsigned_int = 0;
    unsigned int max_unsigned_int = 4294967295;
    signed   int min_signed_int   = -2147483648;
    signed   int max_signed_int   = 2147483647;

    int int_x = 5;
    int int_y = -5;
    int int_a = 1;
    int int_b = 2;
    int int_c = 12345689;
    int int_d = 123456891;

    float float_x = 5;
    float float_y = -5;
    float float_a = 1;
    float float_b = 2;
    float float_c = 12345689;
    float float_d = 123456891;

    puts("\nMin unsigned int:");
    print32(&min_unsigned_int);

    puts("\nMax unsigned int:");
    print32(&max_unsigned_int);

    puts("\nMin signed int:");
    print32(&min_signed_int);

    puts("\nMax signed int:");
    print32(&max_signed_int);

    puts("\nInt x:");
    print32(&int_x);
    puts("Float x:");
    print32(&float_x);

    puts("\nInt y:");
    print32(&int_y);
    puts("Float y:");
    print32(&float_y);

    puts("\nInt a:");
    print32(&int_a);
    puts("Float a:");
    print32(&float_a);

    puts("\nInt b:");
    print32(&int_b);
    puts("Float b:");
    print32(&float_b);

    puts("\nInt c:");
    print32(&int_c);
    puts("Float c:");
    print32(&float_c);

    puts("\nInt d:");
    print32(&int_d);
    puts("Float d:");
    print32(&float_d);

}

void print32(void * p){
    int* int_ptr = (int*) p;
    printf("%08X ",  *int_ptr);
    print_binary(     *int_ptr);
    printf("%010u ",   *int_ptr);
    printf("%+011d ", *int_ptr);

    float* flt_ptr = (float*) p;
    printf("%+A ", *flt_ptr);
    printf("%+E ", *flt_ptr);
    printf("%+f \n", *flt_ptr);
}

void print_binary(int x){
    for (int i = 31; i >= 0; i--){
        if ((x & (1 << i)) != 0){ printf("1"); }
        else{ printf("0"); }
    }
    printf(" ");
}
