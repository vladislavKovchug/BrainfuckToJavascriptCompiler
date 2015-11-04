function BrainfuckCode(params){
	var printCallBack = params.print ? params.print : function(){};
	var resultCallBack = params.result ? params.result : function(){};
	return {
		run : function(){
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
			printCallBack(memory[pointer]);
			pointer++;
			memory[pointer] += -3;
			printCallBack(memory[pointer]);
			memory[pointer] += 7;
			printCallBack(memory[pointer]);
			printCallBack(memory[pointer]);
			memory[pointer] += 3;
			printCallBack(memory[pointer]);
			pointer += 2;
			printCallBack(memory[pointer]);
			pointer--;
			memory[pointer]--;
			printCallBack(memory[pointer]);
			pointer--;
			printCallBack(memory[pointer]);
			memory[pointer] += 3;
			printCallBack(memory[pointer]);
			memory[pointer] += -6;
			printCallBack(memory[pointer]);
			memory[pointer] += -8;
			printCallBack(memory[pointer]);
			pointer += 2;
			memory[pointer]++;
			printCallBack(memory[pointer]);
			pointer++;
			memory[pointer] += 2;
			printCallBack(memory[pointer]);
			resultCallBack();
		}
	}
}

