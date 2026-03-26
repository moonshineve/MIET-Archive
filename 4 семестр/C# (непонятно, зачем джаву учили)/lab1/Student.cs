
namespace lab1
{
    class Student
    {
        private Person _person_info;
        private Education _education_info;
        private int _group_number;
        private Exam[] _passed_exams;

        public Student()
        {
            _person_info = new Person();
            _education_info = Education.Bachelor;
            _group_number = 25;
            _passed_exams = new[] { new Exam() };
        }

        public Student(Person person, Education education, int group_number)
        {
            _person_info = person;
            _education_info = education;
            _group_number = group_number;
            _passed_exams = Array.Empty<Exam>();
        }
        
        public Person PersonInfo 
        { 
            get { return _person_info; } 
            set { _person_info = value; } 
        }

        public Education EducationInfo 
        {
            get { return _education_info; } 
            set { _education_info = value; }
        }

        public int GroupNumber { 
            get { return _group_number; } 
            set { _group_number = value; }
        }

        public Exam[] PassedExams 
        { 
            get { return _passed_exams; } 
            set { _passed_exams = value;}
        }

        public double AverageGrade 
        { 
            get 
            { 
                int grades_sum = 0;
                foreach (Exam exam in _passed_exams)
                {
                    grades_sum += exam.Grade;
                }
                return grades_sum / _passed_exams.Length;
            } 
        }

        public Boolean this [Education education_info]
        {
            get { return education_info == _education_info; }
        }

        public void AddExams(params Exam[] new_exams)
        {
            Exam[] _new_passed_exams = new Exam[_passed_exams.Length + new_exams.Length];
            for (int i = 0; i < _passed_exams.Length; i++)
            {
                _new_passed_exams[i] = _passed_exams[i];
            }

            for (int i = 0; i < new_exams.Length; i++)
            {
                _new_passed_exams[i+_passed_exams.Length] = new_exams[i];
            }

            _passed_exams = _new_passed_exams;
        }

        override public string ToString()
        {
            string exams_str = "";
            int max_exam_string_len = 0;

            foreach (Exam ex in _passed_exams)
            {   
                exams_str += ex.ToString() + "\n";
                max_exam_string_len = Math.Max(max_exam_string_len, ex.ToString().Length);
            }
            string examen_title = " Сданные экзамены ";
            string decor = new string('=', (max_exam_string_len-examen_title.Length)/2);
            return "Имя: " + _person_info.ToString() + "\n" +
                    "Образование: " + Util.education_ru[_education_info] + "\n" + 
                    "Группа: " + _group_number + "\n" + 
                    decor + examen_title + decor + "\n" +
                    exams_str;
        }

        public string ToShortString()
        {
           return "Имя: " + _person_info.ToString() + "\n" +
                    "Образование: " + Util.education_ru[_education_info] + "\n" + 
                    "Группа: " + _group_number + "\n" + 
                    "Средний балл: " + this.AverageGrade + "\n";
        }
    }
}
