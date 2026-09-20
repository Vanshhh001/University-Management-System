package University.Management.System;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/** Reusable validation for fields that have a fixed input format. */
public final class InputValidation {
    private InputValidation() { }

    public static void digitsOnly(JTextField field, int maximumLength) {
        ((PlainDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attributes) throws BadLocationException {
                String inserted = text == null ? "" : text;
                String current = fb.getDocument().getText(0, fb.getDocument().getLength());
                String updated = current.substring(0, offset) + inserted
                        + current.substring(offset + length);
                if (updated.matches("\\d{0," + maximumLength + "}")) {
                    fb.replace(offset, length, inserted, attributes);
                }
            }
        });
    }

    public static void percentage(JTextField field) {
        ((PlainDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attributes) throws BadLocationException {
                String inserted = text == null ? "" : text;
                String current = fb.getDocument().getText(0, fb.getDocument().getLength());
                String updated = current.substring(0, offset) + inserted
                        + current.substring(offset + length);
                if (updated.matches("\\d{0,3}(\\.\\d{0,2})?")) {
                    fb.replace(offset, length, inserted, attributes);
                }
            }
        });
    }

    public static boolean isPhoneNumber(String value) {
        return value != null && value.matches("[6-9]\\d{9}");
    }

    public static boolean isAadhaarNumber(String value) {
        return value != null && value.matches("\\d{12}");
    }

    public static boolean isPercentage(String value) {
        try {
            double percentage = Double.parseDouble(value);
            return percentage >= 0 && percentage <= 100;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static boolean isMark(String value) {
        try {
            int mark = Integer.parseInt(value);
            return mark >= 0 && mark <= 100;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
