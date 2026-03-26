//#include <stdio.h>
//#include <ctype.h>
//#include <limits.h>
//
//void init_array(void* arr, int size, size_t elem_size, const void* value);
//void print_binary_recursive(unsigned int n);
//void print_int_array(void* arr, int size, size_t elem_size, const char* name);
//void print_fp_array(void* arr, int size, size_t elem_size, const char* name);
//
//static int is_valid_unsigned_format(const char* buffer) {
//    const char* p = buffer;
//
//    // пропускаем начальные пробелы
//    while (isspace(*p)) p++;
//
//    if (*p == '\0') return 0;
//
//    if (*p == '-') return 0;
//    if (*p == '+') p++;
//
//    if (!isdigit(*p)) return 0;
//    while (isdigit(*p)) p++;
//
//    while (isspace(*p)) p++;
//    return (*p == '\0' || *p == '\n');
//}
//
//static int is_valid_float_format(const char* buffer) {
//    const char* p = buffer;
//
//    // пропускаем начальные пробелы
//    while (isspace(*p)) p++;
//
//    if (*p == '\0') return 0;
//    if (*p == '+' || *p == '-') p++;
//
//    // должна быть цифра или точка
//    if (!isdigit(*p) && *p != '.') return 0;
//
//    // пропускаем цифры целой части
//    while (isdigit(*p)) p++;
//
//    // дробная часть:
//    if (*p == '.') {
//        p++;
//        while (isdigit(*p)) p++;
//    }
//
//    // экспоненциальная часть (e-10, e+10, e10)
//    if (*p == 'e' || *p == 'E') {
//        p++;
//        if (*p == '+' || *p == '-') p++;
//        if (!isdigit(*p)) return 0;
//        while (isdigit(*p)) p++;
//    }
//
//    // пропускаем пробелы в конце
//    while (isspace(*p)) p++;
//
//    return (*p == '\0' || *p == '\n');
//}
//
//static int is_within_uchar_range(const char* buffer) {
//    unsigned long long val = 0;
//    const char* p = buffer;
//
//    while (isspace(*p)) p++;
//    if (*p == '+') p++;
//
//    while (isdigit(*p)) {
//        val = val * 10 + (*p - '0');
//        if (val > UCHAR_MAX) return 0;  // сразу выход, если превысили
//        p++;
//    }
//
//    return 1;
//}
//
//static int is_within_ushort_range(const char* buffer) {
//    unsigned long long val = 0;
//    const char* p = buffer;
//
//    while (isspace(*p)) p++;
//    if (*p == '+') p++;
//
//    while (isdigit(*p)) {
//        val = val * 10 + (*p - '0');
//        if (val > USHRT_MAX) return 0;
//        p++;
//    }
//
//    return 1;
//}
//
//static int is_within_uint_range(const char* buffer) {
//    unsigned long long val = 0;
//    const char* p = buffer;
//
//    while (isspace(*p)) p++;
//    if (*p == '+') p++;
//
//    while (isdigit(*p)) {
//        val = val * 10 + (*p - '0');
//        if (val > UINT_MAX) return 0;
//        p++;
//    }
//
//    return 1;
//}
//
//void task4() {
//    printf("\nЗадание № 4\n");
//    printf("Ввод нового значения для элемента i=2 каждого массива\n\n");
//
//    const int N = 5;
//    const size_t index = 2;
//
//    unsigned char      Mb[N];
//    unsigned short     Ms[N];
//    unsigned int       Ml[N];
//    unsigned long long Mq[N];
//    float              Mfs[N];
//    double             Mfl[N];
//
//    unsigned char      x_char   = 0xA7;
//    unsigned short     x_short  = 0xC0DE;
//    unsigned int       x_int    = 0xDEADBEEF;
//    unsigned long long x_ll     = 0x000D15A550C1A7ED;
//    float              x_float  = -3.0f/7.0f;
//    double             x_double = -3.0/7.0;
//
//    init_array(Mb,  N, sizeof(unsigned char),      &x_char);
//    init_array(Ms,  N, sizeof(unsigned short),     &x_short);
//    init_array(Ml,  N, sizeof(unsigned int),       &x_int);
//    init_array(Mq,  N, sizeof(unsigned long long), &x_ll);
//    init_array(Mfs, N, sizeof(float),              &x_float);
//    init_array(Mfl, N, sizeof(double),             &x_double);
//
//    printf("Исходные данные:\n\n");
//    print_int_array(Mb, N, sizeof(unsigned char),      "Mb");
//    print_int_array(Ms, N, sizeof(unsigned short),     "Ms");
//    print_int_array(Ml, N, sizeof(unsigned int),       "Ml");
//    print_int_array(Mq, N, sizeof(unsigned long long), "Mq");
//    print_fp_array(Mfs, N, sizeof(float),              "Mfs");
//    print_fp_array(Mfl, N, sizeof(double),             "Mfl");
//
//    printf("\nВвод новых значений:\n\n");
//
//    int result;
//
//
//    printf("\nДля массива Mb:\n");
//    printf("Введите новое значение для Mb[%zu] (0-255): ", index);
//
//    result = scanf("%hhu", &Mb[index]);
//    if (result != 1) {
//        puts("НЕДОПУСТИМЫЙ ВВОД");
//        scanf("%*[^\n]");
//        getchar();
//    } else {
//        getchar();
//        puts("Ввод успешен");
//    }
//
//
//    printf("\nДля массива Ms:\n");
//    printf("Введите новое значение для Ms[%zu] (0-65535): ", index);
//
//    result = scanf("%hu", &Ms[index]);
//    if (result != 1) {
//        puts("НЕДОПУСТИМЫЙ ВВОД");
//        scanf("%*[^\n]");
//        getchar();
//        Ms[index] = x_short;
//    } else {
//        getchar();
//        puts("Ввод успешен");
//    }
//
//
//    printf("\nДля массива Ml:\n");
//    printf("Введите новое значение для Ml[%zu] (0-4294967295): ", index);
//
//    result = scanf("%u", &Ml[index]);
//    if (result != 1) {
//        puts("НЕДОПУСТИМЫЙ ВВОД");
//        scanf("%*[^\n]");
//        getchar();
//        Ml[index] = x_int;
//    } else {
//        getchar();
//        puts("Ввод успешен");
//    }
//
//
//    printf("\nДля массива Mq:\n");
//    printf("Введите новое значение для Mq[%zu] (0-18446744073709551615): ", index);
//
//    result = scanf("%llu", &Mq[index]);
//    if (result != 1) {
//        puts("НЕДОПУСТИМЫЙ ВВОД");
//        scanf("%*[^\n]");
//        getchar();
//        Mq[index] = x_ll;
//    } else {
//        getchar();
//        puts("Ввод успешен");
//    }
//
//
//    printf("\nДля массива Mfs:\n");
//    printf("Введите новое значение для Mfs[%zu] (float): ", index);
//
//    result = scanf("%f", &Mfs[index]);
//    if (result != 1) {
//        puts("НЕДОПУСТИМЫЙ ВВОД");
//        scanf("%*[^\n]");
//        getchar();
//        Mfs[index] = x_float;
//    } else {
//        getchar();
//        puts("Ввод успешен");
//    }
//
//
//    printf("\nДля массива Mfl:\n");
//    printf("Введите новое значение для Mfl[%zu] (double): ", index);
//
//    result = scanf("%lf", &Mfl[index]);
//    if (result != 1) {
//        puts("НЕДОПУСТИМЫЙ ВВОД");
//        scanf("%*[^\n]");
//        getchar();
//        Mfl[index] = x_double;
//    } else {
//        getchar();
//        puts("Ввод успешен");
//    }
//
//    printf("\nПосле ввода:\n\n");
//    print_int_array(Mb, N, sizeof(unsigned char),      "Mb");
//    print_int_array(Ms, N, sizeof(unsigned short),     "Ms");
//    print_int_array(Ml, N, sizeof(unsigned int),       "Ml");
//    print_int_array(Mq, N, sizeof(unsigned long long), "Mq");
//    print_fp_array(Mfs, N, sizeof(float), "Mfs");
//    print_fp_array(Mfl, N, sizeof(double), "Mfl");
//}
