var angle;
var start = "F";
var sentence = start;
var len = 100; // the  starting length
var minimum = 40, maximum = 41; // range of angles

var rules = [];
rules[0] = {
    a: "F",
    b: "FF-[-F+F+F]+[+F-F-F]" 
}
/*rules[1] = {
	a: "X",
	b: "-[+X]++"
}
*/

function generate() {
    len *= 0.54; // decreasing the length of branches
    var nextSentence = "";
    for (var i = 0; i < sentence.length; i++) {
        var current = sentence.charAt(i);
        var found = false;
        for (var j = 0; j < rules.length; j++) {
            if (current == rules[j].a) {
                found = true;
                nextSentence += rules[j].b;
                break;
            }
        }
        if (!found) {
            nextSentence += current;
        }
    }
    sentence = nextSentence;
    createP(sentence); // for paragraphs
    growTree();

}

function growTree() {
    background(51);'ui890'
    //resetMatrix();
    translate(width / 2, height); // to be in the center
    stroke(255, 100); // stroke's colour

    // it changes all branches, because the tree is regenrated
    var randomNumber = Math.floor(Math.random() * (maximum - minimum + 1)) + minimum;
    //createP(randomNumber);
    angle = radians(randomNumber);
    for (var i = 0; i < sentence.length; i++) {
        var current = sentence.charAt(i);

        if (current == "F") {
            line(0, 0, 0, -len);
            translate(0, -len);
        } else if (current == "+") {
            rotate(angle);
        } else if (current == "-") {
            rotate(-angle)
        } else if (current == "[") {
            push(); //remember the current state
        } else if (current == "]") {
            pop(); // teleport to the previous state
        }
        console.log("aaaa");

    }
}

// when our program starts

function setup() {
    var randomNumber = Math.floor(Math.random() * (maximum - minimum + 1)) + minimum;
    console.log(randomNumber);
    createCanvas(600, 600);	
    angle = radians(randomNumber);
    background(51);
    //		createP(start);
    growTree();
    var button = createButton("generate");
    button.mousePressed(generate);
}