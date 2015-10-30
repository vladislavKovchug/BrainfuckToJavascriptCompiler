function payload(){
	var result = '';
	var arr = [];
	for(var i=0; i<30000; i++)arr[i]=0;
	var pointer = 0;
	arr[pointer] += 8;
	while(arr[pointer]){
		pointer++;
		arr[pointer] += 4;
		while(arr[pointer]){
			pointer++;
			arr[pointer] += 2;
			pointer++;
			arr[pointer] += 3;
			pointer++;
			arr[pointer] += 3;
			pointer++;
			arr[pointer]++;
			pointer += -4;
			arr[pointer]--;
		}
		pointer++;
		arr[pointer]++;
		pointer++;
		arr[pointer]++;
		pointer++;
		arr[pointer]--;
		pointer += 2;
		arr[pointer]++;
		while(arr[pointer]){
			pointer--;
		}
		pointer--;
		arr[pointer]--;
	}
	pointer += 2;
	result += String.fromCharCode(arr[pointer]);
	pointer++;
	arr[pointer] += -3;
	result += String.fromCharCode(arr[pointer]);
	arr[pointer] += 7;
	result += String.fromCharCode(arr[pointer]);
	result += String.fromCharCode(arr[pointer]);
	arr[pointer] += 3;
	result += String.fromCharCode(arr[pointer]);
	pointer += 2;
	result += String.fromCharCode(arr[pointer]);
	pointer--;
	arr[pointer]--;
	result += String.fromCharCode(arr[pointer]);
	pointer--;
	result += String.fromCharCode(arr[pointer]);
	arr[pointer] += 3;
	result += String.fromCharCode(arr[pointer]);
	arr[pointer] += -6;
	result += String.fromCharCode(arr[pointer]);
	arr[pointer] += -8;
	result += String.fromCharCode(arr[pointer]);
	pointer += 2;
	arr[pointer]++;
	result += String.fromCharCode(arr[pointer]);
	pointer++;
	arr[pointer] += 2;
	result += String.fromCharCode(arr[pointer]);
	return result;
}

