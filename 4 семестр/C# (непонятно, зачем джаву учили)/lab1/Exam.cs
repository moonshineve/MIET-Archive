
namespace lab1
{
    class Exam
    {
        public string Discipline { set; get;}
        public int Grade { set; get; }
        public DateTime ExamDate { set; get; }
        private const int _maxGrade = 5;
        public Exam()
        {
            Discipline = "Сектоведение";
            Grade = 5;
            ExamDate = Util.DefauldDate;
        }

        public Exam(string discipline, int grade, DateTime exam_date)
        {
            Discipline = discipline;
            Grade = grade;
            if (Util.CheckDate(exam_date)) { ExamDate = exam_date; }
            else { exam_date = Util.DefauldDate; }
        }

        public override string ToString()
        {
            return $"{Discipline} {Grade}/{_maxGrade} {ExamDate.ToString(Util.DateFromat)}"; // ·
        }

        Dictionary<Education, string> EducationStringDict = new Dictionary<Education, string>()
    {
        {Education.Specialist, "Специалист"},
        {Education.Bachelor, "Бакалавр"},
        {Education.SecondEducation, "Вторичное образование"},
    };
    }
}
