var conf= {
	x:0,
	y:0,
	id: 1,
	termDiv:'termDiv',
	frameColor: '#77777a',
	ps: 'TERM:  ',
	historyUnique: true,
	initHandler: initHandler,
	exitHandler: termExit,
	handler: termHandler
}

var term = new Terminal(conf);

function termOpen() {
	TermGlobals.keylock=false;
	term.open();
	setTermLink(false);
}

function setTermLink(v) {
	var linkobj;
	if (document.getElementById) {
		linkobj=document.getElementById('termOpenLink');
	}
	else if (document.all) {
		linkobj=document.all.termOpenLink;
	}
	else {
		linkobj=document.links.termOpenLink;
	}
	if (linkobj) linkobj.className = (v)? 'termopen': 'termopenhidden';
}

function initHandler() {
	this.write( [
		'  Welcome to',
		'                    JJ        SSS',
		'                    JJ      SS   SS',
		'                    JJ       SS',
		'                    JJ         SS',
		'               JJ   JJ      SS   SS',
		'                  JJ          SSS       TERM V0.1',
		'%n%n'
		]);
	this.type('Hallo Welt');
	this.prompt();
}

function termHandler() {
	var line=this.lineBuffer;
	// no action on empty line
	if (line.search(/^\s*$/)==0) {
		this.prompt();
		return;
	}
	this.prompt();
	if (line == 'hallo'){
		this.type('Hallo Christian');
		this.prompt();
		return;
	}
	if (line == 'denon on'){
		this.type('switch on denon');
		this.prompt();
		var urlCall = new XMLHttpRequest();
		urlCall.open("GET", "http://192.168.2.25/goform/formiPhoneAppDirect.xml?Z2ON", true);
		urlCall.send();		
		return;
    }
	if (line == 'denon off'){
		this.type('switch off denon');
		this.prompt();
		var urlCall = new XMLHttpRequest();
		urlCall.open("GET", "http://192.168.2.25/goform/formiPhoneAppDirect.xml?Z2OFF", true);
		urlCall.send();
		return;
    }	
}

function termExit() {
	TermGlobals.keylock=true;
	setTermLink(true);
}