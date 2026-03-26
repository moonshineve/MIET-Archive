//
//void task5(void) {
//    printf("\nЗадание № 5\n");
//    printf("Тип: unsigned short (размер - %zu байта)\n\n", sizeof(unsigned short));
//
//    printf("Для одномерного массива Ms:\n");
//
//    unsigned short Ms[N];
//    for (size_t i = 0; i < N; i++) {
//        Ms[i] = 0xC0DE + i;
//    }
//
//    printf("Элемент    Значение          Адрес\n");
//
//    for (size_t i = 0; i < N; i++) {
//        printf("Ms[%zu]      0x%04hX (%-5hu)    %p\n",
//               i, Ms[i], Ms[i], (void*)&Ms[i]);
//    }
//
//    printf("\nАдрес начала массива Ms:          %p\n", (void*)Ms);
//    printf("Адрес Ms[0]:                      %p\n", (void*)&Ms[0]);
//
//    printf("\nСтатическая матрица MM[R][N]:\n");
//    printf("Размер матрицы: %d строк x %d столбцов\n", R, N);
//
//    unsigned short MM[R][N];
//
//    for (int i = 0; i < R; i++) {
//        for (int j = 0; j < N; j++) {
//            MM[i][j] = 0xC0DE + i*N + j;
//        }
//    }
//
//    printf("\nАдреса элементов матрицы:\n");
//    printf("Элемент         Адрес\n");
//    printf("MM[0][0]        %p\n", (void*)&MM[0][0]);
//    printf("MM[0][1]        %p\n", (void*)&MM[0][1]);
//    printf("MM[1][0]        %p\n", (void*)&MM[1][0]);
//    printf("MM[1][1]        %p\n", (void*)&MM[1][1]);
//
//    printf("\nВоспроизведение структуры матрицы на динамическом массиве\n");
//
//    size_t total_size = R * N * sizeof(unsigned short);
//    printf("Необходимый размер памяти: R * N * sizeof(unsigned short) (%zu байт)\n", total_size);
//
//    unsigned short* M_dyn = (unsigned short*)malloc(total_size);
//    if (M_dyn == NULL) {
//        printf("ОШИБКА: не удалось выделить память!\n");
//        return;
//    }
//
//    printf("\nДинамический массив M_dyn выделен по адресу: %p\n", (void*)M_dyn);
//
//    for (int i = 0; i < R; i++) {
//        for (int j = 0; j < N; j++) {
//            M_dyn[i * N + j] = 0xC0DE + i*N + j;
//        }
//    }
//
//    printf("\nАдреса элементов в динамическом массиве (имитация матрицы):\n");
//    printf("Элемент         Адрес                   Индекс в M_dyn\n");
//    printf("MM[0][0]        %p        [0]\n", (void*)&M_dyn[0]);
//    printf("MM[0][1]        %p        [1]\n", (void*)&M_dyn[1]);
//    printf("MM[1][0]        %p        [%d]\n", (void*)&M_dyn[N], N);
//    printf("MM[1][1]        %p        [%d]\n", (void*)&M_dyn[N+1], N+1);
//
//    printf("\nДля доступа к элементу MM[i][j] в динамическом массиве: idx = i * N + j\n");
//
//    printf("MM[0][0] = 0x%04hX, M_dyn[0] = 0x%04hX\n", MM[0][0], M_dyn[0]);
//    printf("MM[0][1] = 0x%04hX, M_dyn[1] = 0x%04hX\n", MM[0][1], M_dyn[1]);
//    printf("MM[1][0] = 0x%04hX, M_dyn[%d] = 0x%04hX\n", MM[1][0], N, M_dyn[N]);
//    printf("MM[1][1] = 0x%04hX, M_dyn[%d] = 0x%04hX\n", MM[1][1], N+1, M_dyn[N+1]);
//
//    free(M_dyn);
//}
