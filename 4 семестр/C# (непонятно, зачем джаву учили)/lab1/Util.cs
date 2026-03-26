using System;
using System.Collections.Generic;
using System.Text;

namespace lab1
{
    class Util
    {
        public static readonly DateTime DefauldDate = new DateTime(year: 2000, month: 1, day: 1);
        public static readonly string DateFromat = "(dd MMMM yyyy)";

        public static Dictionary<Education, string> education_ru = new()
        {
            {Education.Bachelor, "бакалавр"},
            {Education.SecondEducation, "вторичное образование"},
            {Education.Specialist, "специалист"},
        };
        
        public static bool CheckDate(DateTime date)
        {
            DateTime now = DateTime.Now;

            if (date > now)
            {
                Console.WriteLine("Ошибка: попытка присвоить еще не наступивую дату рождения");
                return false;
            }

            if (date.Year < now.Year - 120)
            {
                Console.WriteLine("Ошибка: попытка присвоить слишком раннюю дату рождения");
                return false;
            }

            return true;
        }
    }
}
