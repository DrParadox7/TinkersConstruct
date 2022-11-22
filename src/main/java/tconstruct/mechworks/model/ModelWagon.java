package tconstruct.mechworks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWagon extends ModelBase {
    ModelRenderer Bottom;
    ModelRenderer Left;
    ModelRenderer Right;
    ModelRenderer Back;
    ModelRenderer Front;
    ModelRenderer Leftpull;
    ModelRenderer Rightpull;
    ModelRenderer LeftWheelAxle;
    ModelRenderer LeftWCenterHbeam;
    ModelRenderer LeftWCenterVtop;
    ModelRenderer LeftWCenterVbottom;
    ModelRenderer LeftWnwH;
    ModelRenderer LeftWnwM;
    ModelRenderer LeftWnwV;
    ModelRenderer LeftWnwEye;
    ModelRenderer LeftWneH;
    ModelRenderer LeftWneM;
    ModelRenderer LeftWneV;
    ModelRenderer LeftWneEye;
    ModelRenderer LeftWswH;
    ModelRenderer LeftWswM;
    ModelRenderer LeftWswV;
    ModelRenderer LeftWswEye;
    ModelRenderer LeftWseH;
    ModelRenderer LeftWseM;
    ModelRenderer LeftWseV;
    ModelRenderer LeftWseEye;
    ModelRenderer RightWheelAxle;
    ModelRenderer RightWCenterHbeam;
    ModelRenderer RightWCenterVtop;
    ModelRenderer RightWCenterVbottom;
    ModelRenderer RightWnwH;
    ModelRenderer RightWnwM;
    ModelRenderer RightWnwV;
    ModelRenderer RightWnwEye;
    ModelRenderer RoghtWneH;
    ModelRenderer RightWneM;
    ModelRenderer RightWneV;
    ModelRenderer RightWneEye;
    ModelRenderer RightWswH;
    ModelRenderer RightWswM;
    ModelRenderer RightWswV;
    ModelRenderer RightWswEye;
    ModelRenderer RightWseH;
    ModelRenderer RightWseM;
    ModelRenderer RightWseV;
    public ModelRenderer RightWseEye;
    ModelRenderer HandBrakeMain;
    ModelRenderer HandbrakeJamb;
    ModelRenderer StandCenterBar;
    ModelRenderer StandLeft;
    ModelRenderer StandRight;

    public ModelWagon() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Bottom = new ModelRenderer(this, 26, 46);
        this.Bottom.addBox(0.0F, 0.0F, 0.0F, 20, 2, 16);
        this.Bottom.setRotationPoint(-10.0F, 4.0F, -8.0F);
        this.Bottom.setTextureSize(64, 32);
        this.Bottom.mirror = true;
        this.setRotation(this.Bottom, 0.0F, 0.0F, 0.0F);
        this.Left = new ModelRenderer(this, 0, 35);
        this.Left.addBox(0.0F, 0.0F, 0.0F, 20, 8, 2);
        this.Left.setRotationPoint(-10.0F, -4.0F, -8.0F);
        this.Left.setTextureSize(64, 32);
        this.Left.mirror = true;
        this.setRotation(this.Left, 0.0F, 0.0F, 0.0F);
        this.Right = new ModelRenderer(this, 83, 35);
        this.Right.addBox(0.0F, 0.0F, 0.0F, 20, 8, 2);
        this.Right.setRotationPoint(-10.0F, -4.0F, 6.0F);
        this.Right.setTextureSize(64, 32);
        this.Right.mirror = true;
        this.setRotation(this.Right, 0.0F, 0.0F, 0.0F);
        this.Back = new ModelRenderer(this, 99, 46);
        this.Back.addBox(-2.0F, -5.0F, 0.0F, 2, 5, 12);
        this.Back.setRotationPoint(10.0F, 4.0F, -6.0F);
        this.Back.setTextureSize(64, 32);
        this.Back.mirror = true;
        this.setRotation(this.Back, 0.0F, 0.0F, 0.0F);
        this.Front = new ModelRenderer(this, 50, 1);
        this.Front.addBox(0.0F, 0.0F, 0.0F, 2, 8, 12);
        this.Front.setRotationPoint(-10.0F, -4.0F, -6.0F);
        this.Front.setTextureSize(64, 32);
        this.Front.mirror = true;
        this.setRotation(this.Front, 0.0F, 0.0F, 0.0F);
        this.Leftpull = new ModelRenderer(this, 0, 30);
        this.Leftpull.addBox(0.0F, 0.0F, 0.0F, 16, 2, 2);
        this.Leftpull.setRotationPoint(-26.0F, 2.0F, -6.0F);
        this.Leftpull.setTextureSize(64, 32);
        this.Leftpull.mirror = true;
        this.setRotation(this.Leftpull, 0.0F, 0.0F, 0.0F);
        this.Rightpull = new ModelRenderer(this, 91, 30);
        this.Rightpull.addBox(0.0F, 0.0F, 0.0F, 16, 2, 2);
        this.Rightpull.setRotationPoint(-26.0F, 2.0F, 4.0F);
        this.Rightpull.setTextureSize(64, 32);
        this.Rightpull.mirror = true;
        this.setRotation(this.Rightpull, 0.0F, 0.0F, 0.0F);
        this.HandBrakeMain = new ModelRenderer(this, 1, 59);
        this.HandBrakeMain.addBox(-6.0F, 0.0F, 0.0F, 11, 1, 1);
        this.HandBrakeMain.setRotationPoint(-9.0F, -3.0F, -9.0F);
        this.HandBrakeMain.setTextureSize(64, 32);
        this.HandBrakeMain.mirror = true;
        this.setRotation(this.HandBrakeMain, 0.0F, 0.0F, 0.0F);
        this.HandbrakeJamb = new ModelRenderer(this, 10, 62);
        this.HandbrakeJamb.addBox(3.0F, 1.0F, 0.0F, 2, 1, 1);
        this.HandbrakeJamb.setRotationPoint(-9.0F, -3.0F, -9.0F);
        this.HandbrakeJamb.setTextureSize(64, 32);
        this.HandbrakeJamb.mirror = true;
        this.setRotation(this.HandbrakeJamb, 0.0F, 0.0F, 0.0F);
        this.LeftWheelAxle = new ModelRenderer(this, 0, 12);
        this.LeftWheelAxle.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 3);
        this.LeftWheelAxle.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWheelAxle.setTextureSize(64, 32);
        this.LeftWheelAxle.mirror = true;
        this.setRotation(this.LeftWheelAxle, 0.0F, 0.0F, 0.0F);
        this.LeftWCenterHbeam = new ModelRenderer(this, 12, 12);
        this.LeftWCenterHbeam.addBox(-8.0F, -2.0F, 0.0F, 16, 4, 1);
        this.LeftWCenterHbeam.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWCenterHbeam.setTextureSize(64, 32);
        this.LeftWCenterHbeam.mirror = true;
        this.setRotation(this.LeftWCenterHbeam, 0.0F, 0.0F, 0.0F);
        this.LeftWCenterVtop = new ModelRenderer(this, 18, 0);
        this.LeftWCenterVtop.addBox(-2.0F, -8.0F, 0.0F, 4, 6, 1);
        this.LeftWCenterVtop.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWCenterVtop.setTextureSize(64, 32);
        this.LeftWCenterVtop.mirror = true;
        this.setRotation(this.LeftWCenterVtop, 0.0F, 0.0F, 0.0F);
        this.LeftWCenterVbottom = new ModelRenderer(this, 18, 22);
        this.LeftWCenterVbottom.addBox(-2.0F, 2.0F, 0.0F, 4, 6, 1);
        this.LeftWCenterVbottom.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWCenterVbottom.setTextureSize(64, 32);
        this.LeftWCenterVbottom.mirror = true;
        this.setRotation(this.LeftWCenterVbottom, 0.0F, 0.0F, 0.0F);
        this.LeftWnwH = new ModelRenderer(this, 0, 7);
        this.LeftWnwH.addBox(-7.0F, -4.0F, 0.0F, 3, 2, 1);
        this.LeftWnwH.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWnwH.setTextureSize(64, 32);
        this.LeftWnwH.mirror = true;
        this.setRotation(this.LeftWnwH, 0.0F, 0.0F, 0.0F);
        this.LeftWnwM = new ModelRenderer(this, 4, 3);
        this.LeftWnwM.addBox(-6.0F, -6.0F, 0.0F, 2, 2, 1);
        this.LeftWnwM.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWnwM.setTextureSize(64, 32);
        this.LeftWnwM.mirror = true;
        this.setRotation(this.LeftWnwM, 0.0F, 0.0F, 0.0F);
        this.LeftWnwV = new ModelRenderer(this, 11, 1);
        this.LeftWnwV.addBox(-4.0F, -7.0F, 0.0F, 2, 3, 1);
        this.LeftWnwV.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWnwV.setTextureSize(64, 32);
        this.LeftWnwV.mirror = true;
        this.setRotation(this.LeftWnwV, 0.0F, 0.0F, 0.0F);
        this.LeftWnwEye = new ModelRenderer(this, 10, 7);
        this.LeftWnwEye.addBox(-4.0F, -4.0F, 0.0F, 1, 1, 1);
        this.LeftWnwEye.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWnwEye.setTextureSize(64, 32);
        this.LeftWnwEye.mirror = true;
        this.setRotation(this.LeftWnwEye, 0.0F, 0.0F, 0.0F);
        this.LeftWneH = new ModelRenderer(this, 40, 7);
        this.LeftWneH.addBox(4.0F, -4.0F, 0.0F, 3, 2, 1);
        this.LeftWneH.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWneH.setTextureSize(64, 32);
        this.LeftWneH.mirror = true;
        this.setRotation(this.LeftWneH, 0.0F, 0.0F, 0.0F);
        this.LeftWneM = new ModelRenderer(this, 37, 3);
        this.LeftWneM.addBox(4.0F, -6.0F, 0.0F, 2, 2, 1);
        this.LeftWneM.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWneM.setTextureSize(64, 32);
        this.LeftWneM.mirror = true;
        this.setRotation(this.LeftWneM, 0.0F, 0.0F, 0.0F);
        this.LeftWneV = new ModelRenderer(this, 29, 1);
        this.LeftWneV.addBox(2.0F, -7.0F, 0.0F, 2, 3, 1);
        this.LeftWneV.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWneV.setTextureSize(64, 32);
        this.LeftWneV.mirror = true;
        this.setRotation(this.LeftWneV, 0.0F, 0.0F, 0.0F);
        this.LeftWneEye = new ModelRenderer(this, 33, 7);
        this.LeftWneEye.addBox(3.0F, -4.0F, 0.0F, 1, 1, 1);
        this.LeftWneEye.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWneEye.setTextureSize(64, 32);
        this.LeftWneEye.mirror = true;
        this.setRotation(this.LeftWneEye, 0.0F, 0.0F, 0.0F);
        this.LeftWswH = new ModelRenderer(this, 0, 19);
        this.LeftWswH.addBox(-7.0F, 2.0F, 0.0F, 3, 2, 1);
        this.LeftWswH.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWswH.setTextureSize(64, 32);
        this.LeftWswH.mirror = true;
        this.setRotation(this.LeftWswH, 0.0F, 0.0F, 0.0F);
        this.LeftWswM = new ModelRenderer(this, 4, 23);
        this.LeftWswM.addBox(-6.0F, 4.0F, 0.0F, 2, 2, 1);
        this.LeftWswM.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWswM.setTextureSize(64, 32);
        this.LeftWswM.mirror = true;
        this.setRotation(this.LeftWswM, 0.0F, 0.0F, 0.0F);
        this.LeftWswV = new ModelRenderer(this, 11, 24);
        this.LeftWswV.addBox(-4.0F, 4.0F, 0.0F, 2, 3, 1);
        this.LeftWswV.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWswV.setTextureSize(64, 32);
        this.LeftWswV.mirror = true;
        this.setRotation(this.LeftWswV, 0.0F, 0.0F, 0.0F);
        this.LeftWswEye = new ModelRenderer(this, 10, 20);
        this.LeftWswEye.addBox(-4.0F, 3.0F, 0.0F, 1, 1, 1);
        this.LeftWswEye.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWswEye.setTextureSize(64, 32);
        this.LeftWswEye.mirror = true;
        this.setRotation(this.LeftWswEye, 0.0F, 0.0F, 0.0F);
        this.LeftWseH = new ModelRenderer(this, 40, 19);
        this.LeftWseH.addBox(4.0F, 2.0F, 0.0F, 3, 2, 1);
        this.LeftWseH.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWseH.setTextureSize(64, 32);
        this.LeftWseH.mirror = true;
        this.setRotation(this.LeftWseH, 0.0F, 0.0F, 0.0F);
        this.LeftWseM = new ModelRenderer(this, 37, 23);
        this.LeftWseM.addBox(4.0F, 4.0F, 0.0F, 2, 2, 1);
        this.LeftWseM.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWseM.setTextureSize(64, 32);
        this.LeftWseM.mirror = true;
        this.setRotation(this.LeftWseM, 0.0F, 0.0F, 0.0F);
        this.LeftWseV = new ModelRenderer(this, 29, 24);
        this.LeftWseV.addBox(2.0F, 4.0F, 0.0F, 2, 3, 1);
        this.LeftWseV.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWseV.setTextureSize(64, 32);
        this.LeftWseV.mirror = true;
        this.setRotation(this.LeftWseV, 0.0F, 0.0F, 0.0F);
        this.LeftWseEye = new ModelRenderer(this, 33, 20);
        this.LeftWseEye.addBox(3.0F, 3.0F, 0.0F, 1, 1, 1);
        this.LeftWseEye.setRotationPoint(0.0F, 5.0F, -10.0F);
        this.LeftWseEye.setTextureSize(64, 32);
        this.LeftWseEye.mirror = true;
        this.setRotation(this.LeftWseEye, 0.0F, 0.0F, 0.0F);
        this.RightWheelAxle = new ModelRenderer(this, 117, 12);
        this.RightWheelAxle.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 3);
        this.RightWheelAxle.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWheelAxle.setTextureSize(64, 32);
        this.RightWheelAxle.mirror = true;
        this.setRotation(this.RightWheelAxle, 0.0F, 0.0F, 0.0F);
        this.RightWCenterHbeam = new ModelRenderer(this, 80, 12);
        this.RightWCenterHbeam.addBox(-8.0F, -2.0F, 0.0F, 16, 4, 1);
        this.RightWCenterHbeam.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWCenterHbeam.setTextureSize(64, 32);
        this.RightWCenterHbeam.mirror = true;
        this.setRotation(this.RightWCenterHbeam, 0.0F, 0.0F, 0.0F);
        this.RightWCenterVtop = new ModelRenderer(this, 98, 0);
        this.RightWCenterVtop.addBox(-2.0F, -8.0F, 0.0F, 4, 6, 1);
        this.RightWCenterVtop.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWCenterVtop.setTextureSize(64, 32);
        this.RightWCenterVtop.mirror = true;
        this.setRotation(this.RightWCenterVtop, 0.0F, 0.0F, 0.0F);
        this.RightWCenterVbottom = new ModelRenderer(this, 98, 22);
        this.RightWCenterVbottom.addBox(-2.0F, 2.0F, 0.0F, 4, 6, 1);
        this.RightWCenterVbottom.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWCenterVbottom.setTextureSize(64, 32);
        this.RightWCenterVbottom.mirror = true;
        this.setRotation(this.RightWCenterVbottom, 0.0F, 0.0F, 0.0F);
        this.RightWnwH = new ModelRenderer(this, 79, 7);
        this.RightWnwH.addBox(4.0F, -4.0F, 0.0F, 3, 2, 1);
        this.RightWnwH.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWnwH.setTextureSize(64, 32);
        this.RightWnwH.mirror = true;
        this.setRotation(this.RightWnwH, 0.0F, 0.0F, 0.0F);
        this.RightWnwM = new ModelRenderer(this, 84, 3);
        this.RightWnwM.addBox(4.0F, -6.0F, 0.0F, 2, 2, 1);
        this.RightWnwM.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWnwM.setTextureSize(64, 32);
        this.RightWnwM.mirror = true;
        this.setRotation(this.RightWnwM, 0.0F, 0.0F, 0.0F);
        this.RightWnwV = new ModelRenderer(this, 91, 1);
        this.RightWnwV.addBox(2.0F, -7.0F, 0.0F, 2, 3, 1);
        this.RightWnwV.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWnwV.setTextureSize(64, 32);
        this.RightWnwV.mirror = true;
        this.setRotation(this.RightWnwV, 0.0F, 0.0F, 0.0F);
        this.RightWnwEye = new ModelRenderer(this, 90, 7);
        this.RightWnwEye.addBox(3.0F, -4.0F, 0.0F, 1, 1, 1);
        this.RightWnwEye.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWnwEye.setTextureSize(64, 32);
        this.RightWnwEye.mirror = true;
        this.setRotation(this.RightWnwEye, 0.0F, 0.0F, 0.0F);
        this.RoghtWneH = new ModelRenderer(this, 119, 7);
        this.RoghtWneH.addBox(-7.0F, -4.0F, 0.0F, 3, 2, 1);
        this.RoghtWneH.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RoghtWneH.setTextureSize(64, 32);
        this.RoghtWneH.mirror = true;
        this.setRotation(this.RoghtWneH, 0.0F, 0.0F, 0.0F);
        this.RightWneM = new ModelRenderer(this, 116, 3);
        this.RightWneM.addBox(-6.0F, -6.0F, 0.0F, 2, 2, 1);
        this.RightWneM.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWneM.setTextureSize(64, 32);
        this.RightWneM.mirror = true;
        this.setRotation(this.RightWneM, 0.0F, 0.0F, 0.0F);
        this.RightWneV = new ModelRenderer(this, 109, 1);
        this.RightWneV.addBox(-4.0F, -7.0F, 0.0F, 2, 3, 1);
        this.RightWneV.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWneV.setTextureSize(64, 32);
        this.RightWneV.mirror = true;
        this.setRotation(this.RightWneV, 0.0F, 0.0F, 0.0F);
        this.RightWneEye = new ModelRenderer(this, 113, 7);
        this.RightWneEye.addBox(-4.0F, -4.0F, 0.0F, 1, 1, 1);
        this.RightWneEye.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWneEye.setTextureSize(64, 32);
        this.RightWneEye.mirror = true;
        this.setRotation(this.RightWneEye, 0.0F, 0.0F, 0.0F);
        this.RightWswH = new ModelRenderer(this, 79, 19);
        this.RightWswH.addBox(4.0F, 2.0F, 0.0F, 3, 2, 1);
        this.RightWswH.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWswH.setTextureSize(64, 32);
        this.RightWswH.mirror = true;
        this.setRotation(this.RightWswH, 0.0F, 0.0F, 0.0F);
        this.RightWswM = new ModelRenderer(this, 84, 23);
        this.RightWswM.addBox(4.0F, 4.0F, 0.0F, 2, 2, 1);
        this.RightWswM.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWswM.setTextureSize(64, 32);
        this.RightWswM.mirror = true;
        this.setRotation(this.RightWswM, 0.0F, 0.0F, 0.0F);
        this.RightWswV = new ModelRenderer(this, 91, 24);
        this.RightWswV.addBox(2.0F, 4.0F, 0.0F, 2, 3, 1);
        this.RightWswV.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWswV.setTextureSize(64, 32);
        this.RightWswV.mirror = true;
        this.setRotation(this.RightWswV, 0.0F, 0.0F, 0.0F);
        this.RightWswEye = new ModelRenderer(this, 90, 20);
        this.RightWswEye.addBox(3.0F, 3.0F, 0.0F, 1, 1, 1);
        this.RightWswEye.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWswEye.setTextureSize(64, 32);
        this.RightWswEye.mirror = true;
        this.setRotation(this.RightWswEye, 0.0F, 0.0F, 0.0F);
        this.RightWseH = new ModelRenderer(this, 119, 19);
        this.RightWseH.addBox(-7.0F, 2.0F, 0.0F, 3, 2, 1);
        this.RightWseH.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWseH.setTextureSize(64, 32);
        this.RightWseH.mirror = true;
        this.setRotation(this.RightWseH, 0.0F, 0.0F, 0.0F);
        this.RightWseM = new ModelRenderer(this, 116, 23);
        this.RightWseM.addBox(-6.0F, 4.0F, 0.0F, 2, 2, 1);
        this.RightWseM.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWseM.setTextureSize(64, 32);
        this.RightWseM.mirror = true;
        this.setRotation(this.RightWseM, 0.0F, 0.0F, 0.0F);
        this.RightWseV = new ModelRenderer(this, 109, 24);
        this.RightWseV.addBox(-4.0F, 4.0F, 0.0F, 2, 3, 1);
        this.RightWseV.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWseV.setTextureSize(64, 32);
        this.RightWseV.mirror = true;
        this.setRotation(this.RightWseV, 0.0F, 0.0F, 0.0F);
        this.RightWseEye = new ModelRenderer(this, 113, 20);
        this.RightWseEye.addBox(-4.0F, 3.0F, 0.0F, 1, 1, 1);
        this.RightWseEye.setRotationPoint(0.0F, 5.0F, 9.0F);
        this.RightWseEye.setTextureSize(64, 32);
        this.RightWseEye.mirror = true;
        this.setRotation(this.RightWseEye, 0.0F, 0.0F, 0.0F);
        this.StandCenterBar = new ModelRenderer(this, 47, 27);
        this.StandCenterBar.addBox(-1.0F, 0.0F, -8.0F, 1, 2, 16);
        this.StandCenterBar.setRotationPoint(-9.0F, 6.0F, 0.0F);
        this.StandCenterBar.setTextureSize(64, 32);
        this.StandCenterBar.mirror = true;
        this.setRotation(this.StandCenterBar, 0.0F, 0.0F, 0.0F);
        this.StandLeft = new ModelRenderer(this, 37, 27);
        this.StandLeft.addBox(-1.0F, 2.0F, -8.0F, 1, 5, 2);
        this.StandLeft.setRotationPoint(-9.0F, 6.0F, 0.0F);
        this.StandLeft.setTextureSize(64, 32);
        this.StandLeft.mirror = true;
        this.setRotation(this.StandLeft, 0.0F, 0.0F, 0.0F);
        this.StandRight = new ModelRenderer(this, 84, 27);
        this.StandRight.addBox(-1.0F, 2.0F, 6.0F, 1, 5, 2);
        this.StandRight.setRotationPoint(-9.0F, 6.0F, 0.0F);
        this.StandRight.setTextureSize(64, 32);
        this.StandRight.mirror = true;
        this.setRotation(this.StandRight, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
        this.LeftWheelAxle.rotateAngleZ = this.LeftWCenterHbeam.rotateAngleZ = this.LeftWCenterVtop.rotateAngleZ =
                this.LeftWCenterVbottom.rotateAngleZ = this.LeftWnwH.rotateAngleZ = this.LeftWnwM.rotateAngleZ =
                        this.LeftWnwV.rotateAngleZ = this.LeftWnwEye.rotateAngleZ = this.LeftWneH.rotateAngleZ =
                                this.LeftWneM.rotateAngleZ = this.LeftWneV.rotateAngleZ = this.LeftWneEye.rotateAngleZ =
                                        this.LeftWswH.rotateAngleZ = this.LeftWswM.rotateAngleZ =
                                                this.LeftWswV.rotateAngleZ = this.LeftWswEye.rotateAngleZ =
                                                        this.LeftWseH.rotateAngleZ = this.LeftWseM.rotateAngleZ =
                                                                this.LeftWseV.rotateAngleZ =
                                                                        this.LeftWseEye.rotateAngleZ =
                                                                                this.RightWheelAxle.rotateAngleZ =
                                                                                        this.RightWCenterHbeam
                                                                                                        .rotateAngleZ =
                                                                                                this.RightWCenterVtop
                                                                                                                .rotateAngleZ =
                                                                                                        this
                                                                                                                        .RightWCenterVbottom
                                                                                                                        .rotateAngleZ =
                                                                                                                this
                                                                                                                                .RightWnwH
                                                                                                                                .rotateAngleZ =
                                                                                                                        this
                                                                                                                                        .RightWnwM
                                                                                                                                        .rotateAngleZ =
                                                                                                                                this
                                                                                                                                                .RightWnwV
                                                                                                                                                .rotateAngleZ =
                                                                                                                                        this
                                                                                                                                                        .RightWnwEye
                                                                                                                                                        .rotateAngleZ =
                                                                                                                                                this
                                                                                                                                                                .RoghtWneH
                                                                                                                                                                .rotateAngleZ =
                                                                                                                                                        this
                                                                                                                                                                        .RightWneM
                                                                                                                                                                        .rotateAngleZ =
                                                                                                                                                                this
                                                                                                                                                                                .RightWneV
                                                                                                                                                                                .rotateAngleZ =
                                                                                                                                                                        this
                                                                                                                                                                                        .RightWneEye
                                                                                                                                                                                        .rotateAngleZ =
                                                                                                                                                                                this
                                                                                                                                                                                                .RightWswH
                                                                                                                                                                                                .rotateAngleZ =
                                                                                                                                                                                        this
                                                                                                                                                                                                        .RightWswM
                                                                                                                                                                                                        .rotateAngleZ =
                                                                                                                                                                                                this
                                                                                                                                                                                                                .RightWswV
                                                                                                                                                                                                                .rotateAngleZ =
                                                                                                                                                                                                        this
                                                                                                                                                                                                                        .RightWswEye
                                                                                                                                                                                                                        .rotateAngleZ =
                                                                                                                                                                                                                this
                                                                                                                                                                                                                                .RightWseH
                                                                                                                                                                                                                                .rotateAngleZ =
                                                                                                                                                                                                                        this
                                                                                                                                                                                                                                        .RightWseM
                                                                                                                                                                                                                                        .rotateAngleZ =
                                                                                                                                                                                                                                this
                                                                                                                                                                                                                                                .RightWseV
                                                                                                                                                                                                                                                .rotateAngleZ =
                                                                                                                                                                                                                                        this
                                                                                                                                                                                                                                                .RightWseEye
                                                                                                                                                                                                                                                .rotateAngleZ;
        this.Bottom.render(var7);
        this.Left.render(var7);
        this.Right.render(var7);
        this.Back.render(var7);
        this.Front.render(var7);
        this.Leftpull.render(var7);
        this.Rightpull.render(var7);
        this.HandBrakeMain.render(var7);
        this.HandbrakeJamb.render(var7);
        this.LeftWheelAxle.render(var7);
        this.LeftWCenterHbeam.render(var7);
        this.LeftWCenterVtop.render(var7);
        this.LeftWCenterVbottom.render(var7);
        this.LeftWnwH.render(var7);
        this.LeftWnwM.render(var7);
        this.LeftWnwV.render(var7);
        this.LeftWnwEye.render(var7);
        this.LeftWneH.render(var7);
        this.LeftWneM.render(var7);
        this.LeftWneV.render(var7);
        this.LeftWneEye.render(var7);
        this.LeftWswH.render(var7);
        this.LeftWswM.render(var7);
        this.LeftWswV.render(var7);
        this.LeftWswEye.render(var7);
        this.LeftWseH.render(var7);
        this.LeftWseM.render(var7);
        this.LeftWseV.render(var7);
        this.LeftWseEye.render(var7);
        this.RightWheelAxle.render(var7);
        this.RightWCenterHbeam.render(var7);
        this.RightWCenterVtop.render(var7);
        this.RightWCenterVbottom.render(var7);
        this.RightWnwH.render(var7);
        this.RightWnwM.render(var7);
        this.RightWnwV.render(var7);
        this.RightWnwEye.render(var7);
        this.RoghtWneH.render(var7);
        this.RightWneM.render(var7);
        this.RightWneV.render(var7);
        this.RightWneEye.render(var7);
        this.RightWswH.render(var7);
        this.RightWswM.render(var7);
        this.RightWswV.render(var7);
        this.RightWswEye.render(var7);
        this.RightWseH.render(var7);
        this.RightWseM.render(var7);
        this.RightWseV.render(var7);
        this.RightWseEye.render(var7);
        this.StandCenterBar.render(var7);
        this.StandLeft.render(var7);
        this.StandRight.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    /**
     * Sets the models various rotation angles.
     */
    @Override
    public void setRotationAngles(
            float var1, float var2, float var3, float var4, float var5, float var6, Entity par7Entity) {
        super.setRotationAngles(var1, var2, var3, var4, var5, var6, par7Entity);
    }
}
