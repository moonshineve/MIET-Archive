
using System.Diagnostics;

namespace lab1
{
    class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("ЗАДАНИЕ 1\n\n");
            RunStudentPart();

            Console.WriteLine("ЗАДАНИЕ 2\n");
            runArrayPart();

        }

        public static void RunStudentPart()
        {
            Student student = new Student();
            Console.Write(student.ToShortString());

            Console.WriteLine("\nИспользование индексатора:");
            foreach (Education education in Enum.GetValues<Education>())
            {
                Console.Write($"{education} {student[education]}\n");
            }

            student.PersonInfo = new Person("Гематоген", "Пупкин", new DateTime(year: 2005, month: 3, day: 12));
            student.EducationInfo = Education.Specialist;
            student.GroupNumber = 67;

            Exam exam1 = new("Белый хакинг", 5, new DateTime(year:2026, month: 1, day: 15));
            student.PassedExams = new[] {exam1};
            Console.WriteLine("\n" + "Объект после применения свойств:");
            Console.WriteLine(student);

            Exam exam2 = new("Тарология", 4, new DateTime(year:2026, month: 1, day: 11));
            Exam exam3 = new("Культура ацтеков", 3, new DateTime(year:2026, month: 1, day: 11));
            student.AddExams(exam2, exam3);
            Console.WriteLine("После использования метода AddExam");
            Console.WriteLine(student);
        }

        public static void runArrayPart()
        {
            Console.Write("Введите количество элементов в массивах: ");
            int size = Convert.ToInt32(Console.ReadLine());
            Console.Write("Введите размерность прямоугольного массива через 1 из разделителей (\" \" или \"_\"): ");
            string[] rectangular_dimansions = Console.ReadLine().Split(' ', '_');
            int rectangular_dimansion_1 = Convert.ToInt32(rectangular_dimansions[0]);
            int rectangular_dimansion_2 = Convert.ToInt32(rectangular_dimansions[1]);

            Stopwatch stopwatch = new Stopwatch();



            // ОДНОМЕРНЫЙ МАССИВ
            Exam[] one_dimension_array = new Exam[size];

            for (int i=0; i < size; i++)
            {
                one_dimension_array[i] = new Exam();

                stopwatch.Start();
                one_dimension_array[i].Discipline = "Матан";
                stopwatch.Stop();
            }

            TimeSpan ts_onedimension = stopwatch.Elapsed;
            Console.WriteLine($"\nВремя для одномерного массива 1x{size}: {ts_onedimension.TotalNanoseconds} нс\n");
            stopwatch.Restart();



            // ПРЯМОУГОЛЬНЫЙ МАССИВ
            Exam[,] rectangular_array = new Exam[rectangular_dimansion_1, rectangular_dimansion_2];
            for (int i = 0; i < rectangular_dimansion_1; i++)
            {
                for (int j = 0; j< size % rectangular_dimansion_2; j++)
                {
                    rectangular_array[i, j] = new Exam();

                    stopwatch.Start();
                    rectangular_array[i, j].Discipline = "Матан";
                    stopwatch.Stop();
                }
            }

            TimeSpan ts_rectangular = stopwatch.Elapsed;
            Console.WriteLine($"Время для прямоугольного массива {rectangular_dimansion_1}"+
                              $"x{rectangular_dimansion_2}: {ts_rectangular.TotalNanoseconds} нс\n");
            stopwatch.Restart();




            // ЗУБЧАТЫЙ МАССИВ
            int elements_count = 0;
            int side_size = 0;
            for (int i=1; i<size; i++)
            {
                elements_count += i;
                if (elements_count >= size) {side_size=i; break;}
            }
            
            Exam[][] jagged_array = new Exam[side_size][];

            for (int i = 0; i<side_size; i++)
            {
                jagged_array[i] = new Exam[i+1];
                for (int j = 0; j <= i; j++)
                {
                    jagged_array[i][j] = new Exam();

                    stopwatch.Start();
                    jagged_array[i][j].Discipline = "Матан";
                    stopwatch.Stop();
                }
            }

            
            TimeSpan ts_jagged = stopwatch.Elapsed;
            Console.WriteLine($"Время для зубчатого массива: {ts_jagged.TotalNanoseconds} нс\n");

            // Console.ReadKey();
        }
    }
}