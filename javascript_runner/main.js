var brainfuckLogo = "\
\
  ____            _        __            _      \r\n\
 |  _ \\          (_)      / _|          | |     \r\n\
 | |_) |_ __ __ _ _ _ __ | |_ _   _  ___| | __  \r\n\
 |  _ <| '__/ _` | | '_ \\|  _| | | |/ __| |/ /  \r\n\
 | |_) | | | (_| | | | | | | | |_| | (__|   <   \r\n\
 |____/|_|  \\__,_|_|_| |_|_|  \\__,_|\\___|_|\\_\\  \r\n\
\r\n\r\n\
type 'run' to start \r\n\
";

$(function($) {
	$('#console').terminal(function(command, term) {
		var echoBuffer = "";
		if (command == 'run') {
			term.echo("Executing...");
			BrainfuckCode({
				print: function(char) {
					echoBuffer += String.fromCharCode(char);
				},
				result: function(value){
					term.echo(echoBuffer);
					term.echo("Brainfuck code terminated.");
				}
			}).run();
		} else {
			term.echo("type 'run' to start");
		}
	}, { prompt: '>', name: 'test', greetings: brainfuckLogo });
});