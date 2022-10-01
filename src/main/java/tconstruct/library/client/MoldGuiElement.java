package tconstruct.library.client;

public class MoldGuiElement extends GuiElement {

    public final int moldIndex;

    public MoldGuiElement(int buttonIconX, int buttonIconY, int moldIndex, String domain, String texture) {
        super(buttonIconX, buttonIconY, domain, texture);

        this.moldIndex = moldIndex;
    }
}
