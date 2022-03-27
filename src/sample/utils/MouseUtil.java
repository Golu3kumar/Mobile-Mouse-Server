package sample.utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MouseUtil {
    public static void moveMouse(int relativeX, int relativeY) {
        try {
            Robot robot = new Robot();
            //System.out.println(relativeX + " " + relativeY);
            SlowMove.moveRelatively(robot, 90, relativeX, relativeY);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void leftClickDown(boolean down) {
        try {
            Robot robot = new Robot();
            if (down) {
                robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
            } else {
                robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
            }
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void rightClick() {
        try {
            Robot robot = new Robot();
            robot.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);

        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void typeText(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("can't type null");
        } else {
            for (int i = 0; i < input.length(); i++) {
                typeSingleChar(input.charAt(i));
                // System.out.println("char: " + input.charAt(i));
            }
        }
    }

    public static void typeSingleChar(char s) {
        //System.out.println("typing char: " + s);

        try {
            Robot robot = new Robot();
            // check if this char should be type with shift key
            if (KeyCodeDict.isShiftKey(s)) {
                int addedCode = KeyCodeDict.getShiftCode(s);
                if (addedCode == -1) {
                    System.out.println("Unknow character");
                }
                shiftAdd(robot, addedCode);
                return;
            }


            //if (Character.isAlphabetic(s)) {
            if (Character.isUpperCase(s)) {
                int keycode = KeyCodeDict.getCodeByChar(s);
                // unknown char
                if (keycode == -1) {
                    System.out.println("Unknow character!!! char: " + s);
                    return;
                }
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(keycode);
                robot.keyRelease(keycode);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            } else {
                int keycode = KeyCodeDict.getCodeByChar(s);
                if (keycode == -1) {
                    System.out.println("Unknow character!!! char: " + s);
                    return;
                }
                robot.keyPress(keycode);
                robot.keyRelease(keycode);
            }

        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void delText() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        } catch (AWTException e) {
            // TODO Auto-generated catch blockykh
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("In Mouse Class");
        // Thread.sleep(6000);
        for (int i = 0; i < 5; i++) {
            typeSingleChar('f');
        }
    }

    public static void moveCenter() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(SlowMove.SCREEN_W / 2, SlowMove.SCREEN_H / 2);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void shiftAdd(Robot robot, int code) {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(code);
        robot.keyRelease(code);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }
}
