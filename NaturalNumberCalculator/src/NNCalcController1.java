import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Keirnan Cagle
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        //Setup Variables for top and bottom
        NaturalNumber topNum = model.top();
        NaturalNumber bottomNum = model.bottom();

        //Update the top and bottom view with the new numbers
        view.updateTopDisplay(topNum);
        view.updateBottomDisplay(bottomNum);

        //Check to see if the disabled buttons can be enabled
        //Make sure the top is greater than the bottom
        view.updateSubtractAllowed(topNum.compareTo(bottomNum) >= 0);
        //Make sure the bottom is not 0
        view.updateDivideAllowed(!bottomNum.isZero());
        //Make sure the bottom is less than the highest possible int
        view.updatePowerAllowed(bottomNum.compareTo(INT_LIMIT) <= 0);
        //Make sure the bottom is greater than two and less than the highest int
        view.updateRootAllowed(bottomNum.compareTo(TWO) >= 0
                && bottomNum.compareTo(INT_LIMIT) <= 0);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        //Get the Numbers
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        //Take the bottom and put it on top
        topNum.copyFrom(bottomNum);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        //Get the numbers
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        //Add the numbers together, display on bottom, and clear the top
        bottomNum.add(topNum);
        topNum.clear();

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        //Get the Numbers
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        //Subtract the numbers, display on bottom, and clear the top
        topNum.subtract(bottomNum);
        bottomNum.transferFrom(topNum);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        //Get the numbers
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        //Multiply the numbers, display on bottom, and clear the top
        topNum.multiply(bottomNum);
        bottomNum.transferFrom(topNum);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {

        //Get the numbers
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        //Divide the numbers, display on bottom, and clear the top
        topNum.divide(bottomNum);
        bottomNum.transferFrom(topNum);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        //Get the numbers
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        //Put the top number to the bottom number power and clear the top
        topNum.power(bottomNum.toInt());
        bottomNum.transferFrom(topNum);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        //Get the numbers
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        //Take the root of the bottom number from the top and clear the top
        topNum.root(bottomNum.toInt());
        bottomNum.transferFrom(topNum);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        //Get the number (Only need the bottom for this method)
        NaturalNumber bottomNum = this.model.bottom();

        //Add the digit to the end of the bottom number
        bottomNum.multiplyBy10(digit);

        updateViewToMatchModel(this.model, this.view);

    }

}
