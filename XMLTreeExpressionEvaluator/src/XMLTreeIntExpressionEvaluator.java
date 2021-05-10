import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Keirnan Cagle
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        //Set up Variables
        int num = 0;
        XMLTree leftChild = exp.child(0);
        //Child 0 and 1 because each tag that has children has exactly 2
        XMLTree rightChild = exp.child(1);

        if (exp.isTag()) {
            //Addition Expression
            if (exp.label().equals("plus")) {
                //If the left child is not a number
                if (!leftChild.hasAttribute("value")) {
                    //Evaluate the left child expression
                    num = evaluate(leftChild);

                    //Add the value of the right child
                    num += Integer.parseInt(rightChild.attributeValue("value"));
                }

                //If the right child is not a number
                if (!rightChild.hasAttribute("value")) {
                    //Evaluate the right child expression
                    num = evaluate(rightChild);

                    //Add the value of the left child
                    num += Integer.parseInt(leftChild.attributeValue("value"));
                }

                //If both children are numbers then just add the values together
                if (leftChild.hasAttribute("value")
                        && rightChild.hasAttribute("value")) {
                    num += Integer.parseInt(leftChild.attributeValue("value"))
                            + Integer.parseInt(
                                    rightChild.attributeValue("value"));
                }
            }

            //Subtraction Expression
            if (exp.label().equals("minus")) {
                //If the left child is not a number
                if (!leftChild.hasAttribute("value")) {
                    //Evaluate the left child expression
                    num = evaluate(leftChild);

                    //Subtracts the right value from the total of the left
                    num -= Integer.parseInt(rightChild.attributeValue("value"));
                }

                //If the right child is not a number
                if (!rightChild.hasAttribute("value")) {
                    //Evaluate the right child expression
                    num = evaluate(rightChild);

                    //Subtracts the total value from the total of the left
                    num = Integer.parseInt(leftChild.attributeValue("value"))
                            - num;
                }

                //If both children are numbers then just subtract the values together
                if (leftChild.hasAttribute("value")
                        && rightChild.hasAttribute("value")) {
                    num += Integer.parseInt(leftChild.attributeValue("value"))
                            - Integer.parseInt(
                                    rightChild.attributeValue("value"));
                }
            }

            //Multiplication Expression
            if (exp.label().equals("times")) {
                //If the left child is not a number
                if (!leftChild.hasAttribute("value")) {
                    //Evaluate the left child expression
                    num = evaluate(leftChild);

                    //Multiply the total by the value of the right child
                    num *= Integer.parseInt(rightChild.attributeValue("value"));
                }

                //If the right child is not a number
                if (!rightChild.hasAttribute("value")) {
                    //Evaluate the right child expression
                    num = evaluate(rightChild);

                    //Multiply the total by the value of the left child
                    num *= Integer.parseInt(leftChild.attributeValue("value"));
                }

                //If both children are numbers then just multiply the values together
                if (leftChild.hasAttribute("value")
                        && rightChild.hasAttribute("value")) {
                    num += Integer.parseInt(leftChild.attributeValue("value"))
                            * Integer.parseInt(
                                    rightChild.attributeValue("value"));
                }
            }

            //Division Expression
            if (exp.label().equals("divide")) {
                //If the left child is not a number
                if (!leftChild.hasAttribute("value")) {
                    //Evaluate the left child expression
                    num = evaluate(leftChild);

                    //Divide the total by the value of the right child
                    num /= Integer.parseInt(rightChild.attributeValue("value"));
                }

                //If the right child is not a number
                if (!rightChild.hasAttribute("value")) {
                    //Evaluate the right child expression
                    num = evaluate(rightChild);

                    //Divide the value of the left child by the total of the right
                    num = Integer.parseInt(leftChild.attributeValue("value"))
                            / num;
                }

                //If both children are numbers then just divide the values by each other
                if (leftChild.hasAttribute("value")
                        && rightChild.hasAttribute("value")) {
                    num += Integer.parseInt(leftChild.attributeValue("value"))
                            / Integer.parseInt(
                                    rightChild.attributeValue("value"));
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