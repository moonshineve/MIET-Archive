#include <limits.h>
#include <float.h>
#include "header.h"

void task3(){
    puts("TASK3 3\n");

    printf("Min char value:               %+d\n", CHAR_MIN);
    printf("Max char value:               %+d\n", CHAR_MAX);
    printf("Min unsigned char value:      %u\n", 0); // Макрос не орпеделен в limits.h
    printf("Max unsigned char value:      %u\n\n", UCHAR_MAX);

    printf("Min short value:              %+d\n", SHRT_MIN);
    printf("Max short value:              %+d\n", SHRT_MAX);
    printf("Min unsigned short value:     %u\n", 0); // Макрос не орпеделен в limits.h
    printf("Max unsigned short value:     %u\n\n", USHRT_MAX);

    printf("Min int value:                %+d\n", INT_MIN);
    printf("Max int value:                %+d\n", INT_MAX);
    printf("Min unsigned int value:       %u\n", 0); // Макрос не орпеделен в limits.h
    printf("Max unsigned int value:       %u\n\n", UINT_MAX);
    
    printf("Min long long value:          %+lld\n", LLONG_MIN);
    printf("Max long long value:          %+lld\n", LLONG_MAX);
    printf("Min unsigned long long value: %llu\n", 0); // Макрос не орпеделен в limits.h
    printf("Max unsigned long long value: %llu\n\n", ULLONG_MAX);

    printf("Min float value:              %+f\n", FLT_MIN);
    printf("Max float value:              %+f\n", FLT_MAX);
    printf("Min double value:             %+e\n", DBL_MIN_10_EXP); 
    printf("Min double value:             %+e\n", DBL_MAX_10_EXP);
    
}