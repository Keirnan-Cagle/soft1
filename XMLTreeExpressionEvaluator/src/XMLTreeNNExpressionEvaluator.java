import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code NaturalNumber}.
 *
 * @author Keirnan Cagle
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        //Set up Variables
        NaturalNumber num = new NaturalNumber2();
        XMLTree leftChild = exp.child(0);
        //Child 0 and 1 because each tag that has children has exactly 2
        XMLTree rightChild = exp.child(1);
        NaturalNumber leftVal = new NaturalNumber2(0);
        NaturalNumber rightVal = new NaturalNumber2(0);

        //If either of the child is a number then it sets the value of the natural number
        //equal to the integer found in the xml
        if (leftChild.hasAttribute("value")) {
            NaturalNumber copyLeftVal = new NaturalNumber2(
                    Integer.parseInt(leftChild.attributeValue("value")));
            leftVal.transferFrom(copyLeftVal);
        }
        if (rightChild.hasAttribute("value")) {
            NaturalNumber copyRightVal = new NaturalNumber2(
                    Integer.parseInt(rightChild.attributeValue("value")));
            rightVal.transferFrom(copyRightVal);
        }

        if (exp.isTag()) {
            //Addition Expression
            if (exp.label().equals("plus")) {
                //If the left child is not a number
                if (!leftChild.hasAttribute("value")) {
                    //Evaluate the left child expression
                    num = evaluate(leftChild);

                    //Add the value of the right child
                    num.add(rightVal);
                }

                //If the right child is not a number
                if (!rightChild.hasAttribute("value")) {
                    //Evaluate the right child expression
                    num = evaluate(rightChild);

                    //Add the value of the left child
                    num.add(leftVal);
                    ;
                }

                //If both children are numbers then just add the values together
                if (leftChild.hasAttribute("value")
                        && rightChild.hasAttribute("value")) {
                    leftVal.add(rightVal);
                    num.add(leftVal);
                }
            }

            //Subtraction Expression
            if (exp.label().equals("minus")) {
                //If the left child is not a number
                if (!leftChild.hasAttribute("value")) {
                    //Evaluate the left child expression
                    num = evaluate(leftChild);

                    //Subtracts the right value from the total of the left
                    num.subtract(rightVal);
                }

                //If the right child is not a number
                if (!rightChild.hasAttribute("value")) {
                    //Evaluate the right child expression
                    num = evaluate(rightChild);

                    //Subtracts the total value from the total of the left
                    leftVal.subtract(num);
                    num.transferFrom(leftVal);
                }

                //If both children are numbers then just subtract the values together
                if (leftChild.hasAttribute("value")
                        && rightChild.hasAttribute("value")) {
                    leftVal.subtract(rightVal);
                    num.add(leftVal);
                }
            }

            //Multiplication Expression
            if (exp.label().equals("times")) {
                //If the left child is not a number
                if (!leftChild.hasAttribute("value")) {
                    //Evaluate the left child expression
                    num = evaluate(leftChild);

                    //Multiply the total by the value of the right child
                    num.multiply(rightVal);
                }

                //If the right child is not a number
                if (!rightChild.hasAttribute("value")) {
                    //Evaluate the right child expression
                    num = evaluate(rightChild);

                    //Multiply the total by the value of the left child
                    num.multiply(leftVal);
                }

                //If both children are numbers then just multiply the values together
                if (leftChild.hasAttribute("value")
                        && rightChild.hasAttribute("value")) {
                    leftVal.multiply(rightVal);
                    num.add(leftVal);
                }
            }

            //Division Expression
            if (exp.label().equals("divide")) {
                //If the left child is not a number
                if (!leftChild.hasAttribute("value")) {
                    //Evaluate the left child expression
                    num = evaluate(leftChild);

                    if (rightVal.isZero()) {
                        //Display error to prevent dividing by 0
                        components.utilities.Reporter.fatalErrorToConsole(
                                "ERROR: Attempted division by 0");

                    } else {
                        //Divide the total by the value of the right child
                        num.divide(rightVal);
                    }

                }

                //If the right child is not a number
                if (!rightChild.hasAttribute("value")) {
                    //Evaluate the right child expression
                    num = evaluate(rightChild);

                    if (num.isZero()) {
                        components.utilities.Reporter.fatalErrorToConsole(
                                "ERROR: Attempted division by 0");
                    } else {
                        //Divide the value of the left child by the total of the right
                        leftVal.divide(num);
                        num.transferFrom(leftVal);
                    }

                }

                //If both children are numbers then just divide the values by each other
                if (leftChild.hasAttribute("value")
                        && rightChild.hasAttribute("value")) {

                    if (rightVal.isZero()) {
                        components.utilities.Reporter.fatalErrorToConsole(
                                "ERROR: Attempted division by 0");
                    } else {
                        leftVal.divide(rightVal);
                        num.add(leftVal);
                    }

                }
            }
        }

        /*
         * This line added just to make the program compilable. Should be
         * replaced with appropriate return statement.
         */
        return num;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}