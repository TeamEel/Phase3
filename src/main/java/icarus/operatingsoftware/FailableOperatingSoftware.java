/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icarus.operatingsoftware;

import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.ComponentFailedException;
import icarus.exceptions.FixAlreadyUnderwayException;
import icarus.exceptions.InvalidComponentException;
import icarus.exceptions.InvalidPumpException;
import icarus.exceptions.InvalidRodsException;
import icarus.exceptions.InvalidValveException;
import icarus.exceptions.NoFixNeededException;
import java.util.Random;

/**
 *
 * @author James
 */
public class FailableOperatingSoftware extends OperatingSoftware {

    private final int pumpCount = 2;
    private final int valveCount = 2;
    private final int maxControlRodPosition = 100;
    private Random randomGenerator = new Random();

    public FailableOperatingSoftware(Plant plant) {
        super(plant);
    }

    @Override
    public void turnOff(int pumpNum) throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        if (!softwareFailure()) {
            super.turnOff(pumpNum);
        }
    }

    @Override
    public void turnOn(int pumpNum) throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        if (!softwareFailure()) {
            super.turnOn(pumpNum);
        }
    }

    @Override
    public void movecontrolrods(int amount) throws InvalidRodsException, ComponentFailedException {
        if (!softwareFailure()) {
            super.movecontrolrods(amount);
        }
    }

    @Override
    public void open(int amount) throws InvalidValveException, AlreadyAtStateException {
        if (!softwareFailure()) {
            super.open(amount);
        }
    }

    @Override
    public void close(int valveNum) throws InvalidValveException, AlreadyAtStateException {
        if (!softwareFailure()) {
            super.close(valveNum);
        }
    }

    @Override
    public void fix(Components component) throws InvalidComponentException, FixAlreadyUnderwayException,
                                                 NoFixNeededException {
        if (!softwareFailure()) {
            super.fix(component);
        }
    }

    @Override
    public void fix(Components component, int pumpNum) throws InvalidPumpException, InvalidComponentException,
                                                              FixAlreadyUnderwayException, NoFixNeededException {
        if (!softwareFailure()) {
            super.fix(component, pumpNum);
        }
    }

    private boolean softwareFailure() {
        /*
         * A 1 in 8 chance of a software failure occuring
         */
        if (randomGenerator.nextInt(8) == 0) {
            /*
             * Do nothing or invoke a random command
             */
            if (randomGenerator.nextBoolean()) {
                randomCommand();
            }
            return true;
        }
        return false;
    }

    private void randomCommand() {
        /*
         * Pick a random command and execute it.
         */
        try {
            switch (randomGenerator.nextInt(5)) {
                case 0:

                    super.turnOn(randomGenerator.nextInt(pumpCount));


                    break;
                case 1:

                    super.turnOff(randomGenerator.nextInt(pumpCount));


                    break;
                case 2:

                    super.open(randomGenerator.nextInt(valveCount));

                    break;
                case 3:

                    super.close(randomGenerator.nextInt(valveCount));


                    break;
                case 4:

                    super.movecontrolrods(randomGenerator.nextInt(maxControlRodPosition + 1));
                    break;
            }
        } catch (Exception e) {/*Do nothing*/

        }
  
    }
}
