class UnaryTuringMachine:
    def __init__(self, digit_str, head_position=0):

        self.tape = list(digit_str)
        self.head = head_position
        self.state = 'q0'
        self.steps = 0
        self.max_steps = 1000
        
    def move(self, direction):
        "головка L - влево, R - вправо, N - на месте"
        if direction == 'L':
            self.head -= 1
            if self.head < 0:
                self.tape.insert(0, 'λ')
                self.head = 0
        elif direction == 'R':
            self.head += 1
            if self.head >= len(self.tape):
                self.tape.append('λ')
        # N - не двигаемся
    
    def get_transition(self, state, symbol):
        transitions = {
            'q0': {
                '0': ('0', 'R', 'q0'),
                '1': ('1', 'R', 'q0'),
                '2': ('2', 'R', 'q0'),
                '3': ('3', 'R', 'q0'),
                '4': ('4', 'R', 'q0'),
                '5': ('5', 'R', 'q0'),
                '6': ('6', 'R', 'q0'),
                '7': ('7', 'R', 'q0'),
                '8': ('8', 'R', 'q0'),
                'λ': ('*', 'R', 'q1'),  # конец числа - ставится разделитель
                '*': ('*', 'R', 'q0')   # пропуск знака *
            },
            'q1': {
                '0': ('0', 'N', 'qk'),  # qk - конечное состояние
                '1': ('1', 'R', 'q2'),
                '2': ('2', 'R', 'q3'),
                '3': ('3', 'R', 'q4'),
                '4': ('4', 'R', 'q5'),
                '5': ('5', 'R', 'q6'),
                '6': ('6', 'R', 'q7'),
                '7': ('7', 'R', 'q8'),
                '8': ('8', 'R', 'q9'),
                'λ': ('λ', 'L', 'q1'),   # пустота - идём влево
                '*': ('*', 'L', 'q1')    # разделитель - идём влево
            },

            'q2': {'λ': ('1', 'R', 'qk'), '*': ('*', 'R', 'q2')}, 
            'q3': {'λ': ('1', 'R', 'q2'), '*': ('*', 'R', 'q3')},
            'q4': {'λ': ('1', 'R', 'q3'), '*': ('*', 'R', 'q4')},
            'q5': {'λ': ('1', 'R', 'q4'), '*': ('*', 'R', 'q5')},
            'q6': {'λ': ('1', 'R', 'q5'), '*': ('*', 'R', 'q6')},
            'q7': {'λ': ('1', 'R', 'q6'), '*': ('*', 'R', 'q7')},
            'q8': {'λ': ('1', 'R', 'q6'), '*': ('*', 'R', 'q8')},
            'q9': {'λ': ('1', 'R', 'q6'), '*': ('*', 'R', 'q9')},

            'qk': {}
        }
        
        if state in transitions and symbol in transitions[state]:
            return transitions[state][symbol]
        return None
    
    def run(self):

        print(f"Исходная цифра: {self.tape[0] if self.tape else 'λ'}")
        
        while self.state != 'qk' and self.steps < self.max_steps:
            self.steps += 1
            current_symbol = self.tape[self.head]
            
            transition = self.get_transition(self.state, current_symbol)
            
            if transition is None:
                print(f"Нет перехода для состояния {self.state} и символа '{current_symbol}'")
                break
            
            new_symbol, direction, new_state = transition
            
            self.tape[self.head] = new_symbol
            self.move(direction)
            self.state = new_state
        
        tape_str = ''.join(self.tape)
        
        star_pos = tape_str.find('*')
        if star_pos != -1:
            unary_part = tape_str[star_pos+1:].replace('λ', '')
            unary_count = unary_part.count('1')
            
            print(f"Унарная запись (единицами '1'): {unary_part}")
            print(f"Количество единиц: {unary_count}")
            
        else:
            print(f"Полная лента: {tape_str}")
        
        print(f"Всего шагов: {self.steps}")
        return self.tape


def test_digit_conversion(digit):

    if digit not in '012345678':
        print(f"Ошибка: '{digit}' не поддерживается (только 0-8)")
        return
    
    tape = [digit, 'λ', 'λ']
    tm = UnaryTuringMachine(tape, head_position=0)
    tm.run()


if __name__ == "__main__":
    test_digit_conversion("3")
