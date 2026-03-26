
namespace lab1
{
    class Person
    {
        private string _first_name;
        private string _last_name;
        private DateTime _birth_date;

        public Person()
        {
            _first_name = "Кузьма";
            _last_name = "Борщ";
            _birth_date = Util.DefauldDate;
        }

        public Person(string first_name, string last_name, DateTime birth_date)
        {
            _first_name = first_name;
            _last_name = last_name;
            if (Util.CheckDate(birth_date)) { _birth_date = birth_date; }
            else { _birth_date = Util.DefauldDate; }
        }

        public string FirstName
        {
            get { return _first_name; }
            set { _first_name = value; }
        }

        public string LastName
        {
            get { return _last_name; }
            set { _last_name = value; }
        }

        public DateTime BirthDate
        {
            get { return _birth_date; }
            set { if (Util.CheckDate(_birth_date)) {_birth_date = value;} }
        }

        public int BirthYear
        {
            get { return _birth_date.Year; }
            set
            {
                DateTime new_date = new DateTime(year: value, month: _birth_date.Month, day: _birth_date.Day);
                if (Util.CheckDate(new_date))
                {
                    _birth_date = new_date;
                }
            }
        }

        public override string ToString()
        {
            return $"{_first_name} {_last_name} {_birth_date.ToString(Util.DateFromat)}";
        }

        public virtual string ToShortString()
        {
            return $"{_first_name} {_last_name}";
        }
    }
}
