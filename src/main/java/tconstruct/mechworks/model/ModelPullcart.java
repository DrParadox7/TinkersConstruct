package tconstruct.mechworks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPullcart extends ModelBase {

    ModelRenderer BackPanel;
    ModelRenderer FrontPanel;
    ModelRenderer RightPanel;
    ModelRenderer LeftPanel;
    ModelRenderer BottomPanel;
    ModelRenderer RightHandle;
    ModelRenderer LeftHandle;
    ModelRenderer RightSpokeUp;
    ModelRenderer RightSpokeRight;
    ModelRenderer RightSpokeDown;
    ModelRenderer RightSpokeLeft;
    ModelRenderer RightAxle;
    ModelRenderer WheelRight1;
    ModelRenderer WheelRight2;
    ModelRenderer WheelRight3;
    ModelRenderer WheelRight4;
    ModelRenderer WheelRight5;
    ModelRenderer WheelRight6;
    ModelRenderer WheelRight7;
    ModelRenderer WheelRight8;
    ModelRenderer WheelRight9;
    ModelRenderer WheelRight10;
    ModelRenderer WheelRight11;
    ModelRenderer WheelRight12;
    ModelRenderer LeftSpokeRight;
    ModelRenderer LeftSpokeLeft;
    ModelRenderer LeftSpokeDown;
    ModelRenderer LeftSpokeUp;
    ModelRenderer LeftAxle;
    ModelRenderer WheelLeft1;
    ModelRenderer WheelLeft2;
    ModelRenderer WheelLeft3;
    ModelRenderer WheelLeft4;
    ModelRenderer WheelLeft5;
    ModelRenderer WheelLeft6;
    ModelRenderer WheelLeft7;
    ModelRenderer WheelLeft8;
    ModelRenderer WheelLeft9;
    ModelRenderer WheelLeft10;
    ModelRenderer WheelLeft11;
    ModelRenderer WheelLeft12;

    public ModelPullcart() {
        textureWidth = 128;
        textureHeight = 64;

        BackPanel = new ModelRenderer(this, 0, 12);
        BackPanel.addBox(-10F, 0F, 0F, 20, 10, 2);
        BackPanel.setRotationPoint(0F, -8F, 8F);
        setRotation(BackPanel, 0F, 0F, 0F);
        FrontPanel = new ModelRenderer(this, 0, 0);
        FrontPanel.addBox(-10F, 0F, 0F, 20, 10, 2);
        FrontPanel.setRotationPoint(0F, -8F, -10F);
        setRotation(FrontPanel, 0F, 0F, 0F);
        RightPanel = new ModelRenderer(this, 0, 35);
        RightPanel.addBox(0F, 0F, -10F, 2, 8, 20);
        RightPanel.setRotationPoint(-12F, -6F, 0F);
        setRotation(RightPanel, 0F, 0F, 0F);
        LeftPanel = new ModelRenderer(this, 24, 15);
        LeftPanel.addBox(0F, 0F, -10F, 2, 8, 20);
        LeftPanel.setRotationPoint(10F, -6F, 0F);
        setRotation(LeftPanel, 0F, 0F, 0F);
        BottomPanel = new ModelRenderer(this, 40, 43);
        BottomPanel.addBox(-12F, 0F, -10F, 24, 1, 20);
        BottomPanel.setRotationPoint(0F, 2F, 0F);
        setRotation(BottomPanel, 0F, 0F, 0F);
        RightHandle = new ModelRenderer(this, 92, 33);
        RightHandle.addBox(0F, 0F, 0F, 16, 2, 2);
        RightHandle.setRotationPoint(-28F, 0F, -7F);
        setRotation(RightHandle, 0F, 0F, 0F);
        LeftHandle = new ModelRenderer(this, 92, 38);
        LeftHandle.addBox(0F, 0F, 0F, 16, 2, 2);
        LeftHandle.setRotationPoint(-28F, 0F, 5F);
        setRotation(LeftHandle, 0F, 0F, 0F);
        RightSpokeUp = new ModelRenderer(this, 99, 3);
        RightSpokeUp.addBox(-2F, -8F, 0F, 4, 6, 1);
        RightSpokeUp.setRotationPoint(0F, 3F, -11F);
        setRotation(RightSpokeUp, 0F, 0F, 0F);
        RightSpokeRight = new ModelRenderer(this, 112, 15);
        RightSpokeRight.addBox(2F, -2F, 0F, 6, 4, 1);
        RightSpokeRight.setRotationPoint(0F, 3F, -11F);
        setRotation(RightSpokeRight, 0F, 0F, 0F);
        RightSpokeDown = new ModelRenderer(this, 99, 25);
        RightSpokeDown.addBox(-2F, 2F, 0F, 4, 6, 1);
        RightSpokeDown.setRotationPoint(0F, 3F, -11F);
        setRotation(RightSpokeDown, 0F, 0F, 0F);
        RightSpokeLeft = new ModelRenderer(this, 82, 15);
        RightSpokeLeft.addBox(-8F, -2F, 0F, 6, 4, 1);
        RightSpokeLeft.setRotationPoint(0F, 3F, -11F);
        setRotation(RightSpokeLeft, 0F, 0F, 0F);
        RightAxle = new ModelRenderer(this, 98, 14);
        RightAxle.addBox(-2F, -2F, 0F, 4, 4, 2);
        RightAxle.setRotationPoint(0F, 3F, -12F);
        setRotation(RightAxle, 0F, 0F, 0F);
        WheelRight1 = new ModelRenderer(this, 110, 3);
        WheelRight1.addBox(2F, -7F, 0F, 2, 2, 1);
        WheelRight1.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight1, 0F, 0F, 0F);
        WheelRight2 = new ModelRenderer(this, 117, 5);
        WheelRight2.addBox(4F, -6F, 0F, 2, 2, 1);
        WheelRight2.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight2, 0F, 0F, 0F);
        WheelRight3 = new ModelRenderer(this, 122, 9);
        WheelRight3.addBox(5F, -4F, 0F, 2, 2, 1);
        WheelRight3.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight3, 0F, 0F, 0F);
        WheelRight4 = new ModelRenderer(this, 122, 23);
        WheelRight4.addBox(5F, 2F, 0F, 2, 2, 1);
        WheelRight4.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight4, 0F, 0F, 0F);
        WheelRight5 = new ModelRenderer(this, 117, 27);
        WheelRight5.addBox(4F, 4F, 0F, 2, 2, 1);
        WheelRight5.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight5, 0F, 0F, 0F);
        WheelRight6 = new ModelRenderer(this, 110, 29);
        WheelRight6.addBox(2F, 5F, 0F, 2, 2, 1);
        WheelRight6.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight6, 0F, 0F, 0F);
        WheelRight7 = new ModelRenderer(this, 92, 29);
        WheelRight7.addBox(-4F, 5F, 0F, 2, 2, 1);
        WheelRight7.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight7, 0F, 0F, 0F);
        WheelRight8 = new ModelRenderer(this, 85, 27);
        WheelRight8.addBox(-6F, 4F, 0F, 2, 2, 1);
        WheelRight8.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight8, 0F, 0F, 0F);
        WheelRight9 = new ModelRenderer(this, 80, 23);
        WheelRight9.addBox(-7F, 2F, 0F, 2, 2, 1);
        WheelRight9.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight9, 0F, 0F, 0F);
        WheelRight10 = new ModelRenderer(this, 80, 9);
        WheelRight10.addBox(-7F, -4F, 0F, 2, 2, 1);
        WheelRight10.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight10, 0F, 0F, 0F);
        WheelRight11 = new ModelRenderer(this, 85, 5);
        WheelRight11.addBox(-6F, -6F, 0F, 2, 2, 1);
        WheelRight11.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight11, 0F, 0F, 0F);
        WheelRight12 = new ModelRenderer(this, 92, 3);
        WheelRight12.addBox(-4F, -7F, 0F, 2, 2, 1);
        WheelRight12.setRotationPoint(0F, 3F, -11F);
        setRotation(WheelRight12, 0F, 0F, 0F);
        LeftSpokeRight = new ModelRenderer(this, 82, 15);
        LeftSpokeRight.addBox(-8F, -2F, 0F, 6, 4, 1);
        LeftSpokeRight.setRotationPoint(0F, 3F, 10F);
        setRotation(LeftSpokeRight, 0F, 0F, 0F);
        LeftSpokeLeft = new ModelRenderer(this, 112, 15);
        LeftSpokeLeft.addBox(2F, -2F, 0F, 6, 4, 1);
        LeftSpokeLeft.setRotationPoint(0F, 3F, 10F);
        setRotation(LeftSpokeLeft, 0F, 0F, 0F);
        LeftSpokeDown = new ModelRenderer(this, 99, 25);
        LeftSpokeDown.addBox(-2F, 2F, 0F, 4, 6, 1);
        LeftSpokeDown.setRotationPoint(0F, 3F, 10F);
        setRotation(LeftSpokeDown, 0F, 0F, 0F);
        LeftSpokeUp = new ModelRenderer(this, 99, 3);
        LeftSpokeUp.addBox(-2F, -8F, 0F, 4, 6, 1);
        LeftSpokeUp.setRotationPoint(0F, 3F, 10F);
        setRotation(LeftSpokeUp, 0F, 0F, 0F);
        LeftAxle = new ModelRenderer(this, 98, 14);
        LeftAxle.addBox(-2F, -2F, 0F, 4, 4, 2);
        LeftAxle.setRotationPoint(0F, 3F, 10F);
        setRotation(LeftAxle, 0F, 0F, 0F);
        WheelLeft1 = new ModelRenderer(this, 92, 3);
        WheelLeft1.addBox(-4F, -7F, 0F, 2, 2, 1);
        WheelLeft1.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft1, 0F, 0F, 0F);
        WheelLeft2 = new ModelRenderer(this, 117, 5);
        WheelLeft2.addBox(-6F, -6F, 0F, 2, 2, 1);
        WheelLeft2.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft2, 0F, 0F, 0F);
        WheelLeft3 = new ModelRenderer(this, 80, 9);
        WheelLeft3.addBox(-7F, -4F, 0F, 2, 2, 1);
        WheelLeft3.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft3, 0F, 0F, 0F);
        WheelLeft4 = new ModelRenderer(this, 80, 23);
        WheelLeft4.addBox(-7F, 2F, 0F, 2, 2, 1);
        WheelLeft4.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft4, 0F, 0F, 0F);
        WheelLeft5 = new ModelRenderer(this, 117, 27);
        WheelLeft5.addBox(-6F, 4F, 0F, 2, 2, 1);
        WheelLeft5.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft5, 0F, 0F, 0F);
        WheelLeft6 = new ModelRenderer(this, 110, 29);
        WheelLeft6.addBox(-4F, 5F, 0F, 2, 2, 1);
        WheelLeft6.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft6, 0F, 0F, 0F);
        WheelLeft7 = new ModelRenderer(this, 92, 29);
        WheelLeft7.addBox(2F, 5F, 0F, 2, 2, 1);
        WheelLeft7.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft7, 0F, 0F, 0F);
        WheelLeft8 = new ModelRenderer(this, 85, 27);
        WheelLeft8.addBox(4F, 4F, 0F, 2, 2, 1);
        WheelLeft8.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft8, 0F, 0F, 0F);
        WheelLeft9 = new ModelRenderer(this, 122, 23);
        WheelLeft9.addBox(5F, 2F, 0F, 2, 2, 1);
        WheelLeft9.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft9, 0F, 0F, 0F);
        WheelLeft10 = new ModelRenderer(this, 122, 9);
        WheelLeft10.addBox(5F, -4F, 0F, 2, 2, 1);
        WheelLeft10.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft10, 0F, 0F, 0F);
        WheelLeft11 = new ModelRenderer(this, 85, 5);
        WheelLeft11.addBox(4F, -6F, 0F, 2, 2, 1);
        WheelLeft11.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft11, 0F, 0F, 0F);
        WheelLeft12 = new ModelRenderer(this, 110, 3);
        WheelLeft12.addBox(2F, -7F, 0F, 2, 2, 1);
        WheelLeft12.setRotationPoint(0F, 3F, 10F);
        setRotation(WheelLeft12, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        BackPanel.render(f5);
        FrontPanel.render(f5);
        RightPanel.render(f5);
        LeftPanel.render(f5);
        BottomPanel.render(f5);
        RightHandle.render(f5);
        LeftHandle.render(f5);
        RightSpokeUp.render(f5);
        RightSpokeRight.render(f5);
        RightSpokeDown.render(f5);
        RightSpokeLeft.render(f5);
        RightAxle.render(f5);
        WheelRight1.render(f5);
        WheelRight2.render(f5);
        WheelRight3.render(f5);
        WheelRight4.render(f5);
        WheelRight5.render(f5);
        WheelRight6.render(f5);
        WheelRight7.render(f5);
        WheelRight8.render(f5);
        WheelRight9.render(f5);
        WheelRight10.render(f5);
        WheelRight11.render(f5);
        WheelRight12.render(f5);
        LeftSpokeRight.render(f5);
        LeftSpokeLeft.render(f5);
        LeftSpokeDown.render(f5);
        LeftSpokeUp.render(f5);
        LeftAxle.render(f5);
        WheelLeft1.render(f5);
        WheelLeft2.render(f5);
        WheelLeft3.render(f5);
        WheelLeft4.render(f5);
        WheelLeft5.render(f5);
        WheelLeft6.render(f5);
        WheelLeft7.render(f5);
        WheelLeft8.render(f5);
        WheelLeft9.render(f5);
        WheelLeft10.render(f5);
        WheelLeft11.render(f5);
        WheelLeft12.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
