function BrainfuckCode(resultCallBack){
	return {
		run : function(){
			var result = '';
			var memory = Array.apply(null, new Array(30000)).map(Number.prototype.valueOf,0);;
			var pointer = 0;
			memory[pointer] += 8;
			while(memory[pointer])
{
				pointer++;
				memory[pointer] += 4;
				while(memory[pointer])
{
					pointer++;
					memory[pointer] += 2;
					pointer++;
					memory[pointer] += 3;
					pointer++;
					memory[pointer] += 3;
					pointer++;
					memory[pointer]++;
					pointer += -4;
					memory[pointer]--;
				}
				pointer++;
				memory[pointer]++;
				pointer++;
				memory[pointer]++;
				pointer++;
				memory[pointer]--;
				pointer += 2;
				memory[pointer]++;
				while(memory[pointer])
{
					pointer--;
				}
				pointer--;
				memory[pointer]--;
			}
			pointer += 2;
			result += String.fromCharCode(memory[pointer]);
			pointer++;
			memory[pointer] += -3;
			result += String.fromCharCode(memory[pointer]);
			memory[pointer] += 7;
			result += String.fromCharCode(memory[pointer]);
			result += String.fromCharCode(memory[pointer]);
			memory[pointer] += 3;
			result += String.fromCharCode(memory[pointer]);
			pointer += 2;
			result += String.fromCharCode(memory[pointer]);
			pointer--;
			memory[pointer]--;
			result += String.fromCharCode(memory[pointer]);
			pointer--;
			result += String.fromCharCode(memory[pointer]);
			memory[pointer] += 3;
			result += String.fromCharCode(memory[pointer]);
			memory[pointer] += -6;
			result += String.fromCharCode(memory[pointer]);
			memory[pointer] += -8;
			result += String.fromCharCode(memory[pointer]);
			pointer += 2;
			memory[pointer]++;
			result += String.fromCharCode(memory[pointer]);
			pointer++;
			memory[pointer] += 2;
			result += String.fromCharCode(memory[pointer]);
			resultCallBack(result);
		}
	}
}

