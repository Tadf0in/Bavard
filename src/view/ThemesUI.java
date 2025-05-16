package view;

import java.util.EnumMap;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Bavard;
import model.ThemesEnum;

@SuppressWarnings("serial")
public class ThemesUI extends JPanel {
	private Bavard bavard;
    private EnumMap<ThemesEnum, JCheckBox> checkBoxes;
	
	public ThemesUI(Bavard bavard) {
		this.bavard = bavard;
        this.checkBoxes = new EnumMap<>(ThemesEnum.class);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel themeLabel = new JLabel("Thèmes suivis :");
        this.add(themeLabel);

        for (ThemesEnum theme : ThemesEnum.values()) {
            JCheckBox checkBox = new JCheckBox(theme.toString());
            checkBox.setSelected(this.bavard.getThemesSuivis().contains(theme));
            checkBoxes.put(theme, checkBox);
            this.add(checkBox);

            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    bavard.addTheme(theme);
                } else {
                    bavard.removeTheme(theme);
                }
            });
        }
	}
}
