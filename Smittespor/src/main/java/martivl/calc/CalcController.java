package martivl.calc;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;

public class CalcController {

    private Calc calc;
    private String nmb; //number that is made while pressing
    public CalcController() {
        calc = new Calc(0.0, 0.0, 0.0);
    }

    public Calc getCalc() {
        return calc;
    }

    public void setCalc(Calc calc) {
        this.calc = calc;
        updateOperandsView();
    }

    @FXML
    private ListView<Double> operandsView;

    @FXML
    private Label operandView;

    @FXML
    void initialize() {
        updateOperandsView();
    }

    private void updateOperandsView() {
        List<Double> operands = operandsView.getItems();
        operands.clear();
        int elementCount = Math.min(calc.getOperandCount(), 3);
        for (int i = 0; i < elementCount; i++) {
            operands.add(calc.peekOperand(elementCount - i - 1));
        }
    }

    private String getOperandString() {
        return operandView.getText();
    }

    private boolean hasOperand() {
        return ! getOperandString().isBlank();
    }

    private double getOperand() {
        return Double.valueOf(operandView.getText());
    }
    
    private void setOperand(String operandString) {
        operandView.setText(operandString);
    }

    @FXML
    void handleEnter() {
        if (hasOperand()) {
            calc.pushOperand(getOperand());
        } else {
            calc.dup();
        }
        setOperand("");
        updateOperandsView();
    }

    private void appendToOperand(String s) {
        // TODO
    }

    @FXML
    void handleDigit(ActionEvent ae) {
        if (ae.getSource() instanceof Labeled l) {
            String labelText = l.getText();
            String oldText = this.getOperandString();
            setOperand(oldText + labelText);
        }
    }

    @FXML
    void handlePoint() {
        var operandString = getOperandString();
        String newString;
        if (operandString.contains(".")) {
            newString = operandString.split("\\.")[0] + ".";
        } else {
            newString = operandString + ".";
        }
        setOperand(newString);
    }

    @FXML
    void handleClear() {
        // TODO clear operand
        setOperand("");
    }

    @FXML
    void handleSwap() {
        calc.swap();
        updateOperandsView();
    }

    private void performOperation(UnaryOperator<Double> op) {
        // TODO
    }

    private void performOperation(boolean swap, BinaryOperator<Double> op) {
        if (hasOperand()) {
            // TODO push operand first
        }
        // TODO perform operation, but swap first if needed
    }
    
    @FXML
    void handleOpAdd() {
        handleEnter();
        if(calc.getOperandCount() > 1) {
            calc.performOperation((x1, x2) -> x1 + x2);
        }
        this.updateOperandsView();
    }

    @FXML
    void handleOpSub() {
        handleEnter();
        if(calc.getOperandCount() > 1) {
            calc.performOperation((x1, x2) -> x1 - x2);
        }
        this.updateOperandsView();
    }

    @FXML
    void handleOpMult() {
        handleEnter();
        if(calc.getOperandCount() > 1) {
            calc.performOperation((x1, x2) -> x1 * x2);
        }
        this.updateOperandsView();
    }

    @FXML
    void handleOpDiv() {
        handleEnter();
        if(calc.getOperandCount() > 1) {
            calc.performOperation((x1, x2) -> x1 / x2);
        }
        this.updateOperandsView();
    }

    @FXML
    void handleOpSqrt() {
        handleEnter();
        if(calc.getOperandCount() >= 1) {
            calc.performOperation(x -> Math.sqrt(x));
        }
        this.updateOperandsView();
    }

    @FXML
    void handleOpPi() {
        setOperand("" + Math.PI);
        this.handleEnter();
    }
}
