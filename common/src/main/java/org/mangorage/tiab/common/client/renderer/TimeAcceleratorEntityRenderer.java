package org.mangorage.tiab.common.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.mangorage.tiab.common.entities.TimeAcceleratorEntity;

public class TimeAcceleratorEntityRenderer extends EntityRenderer<TimeAcceleratorEntity> {
    public TimeAcceleratorEntityRenderer(EntityRendererProvider.Context erp) {
        super(erp);

    }

    @Override
    public void render(TimeAcceleratorEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {

        // Show The Rate
        String timeRate = "x" + 2 * entity.getTimeRate();
        float paddingLeftRight = 2 * entity.getTimeRate() < 10 ? 0.11F : 0.19F;

        drawText(matrixStack, bufferIn, timeRate, new Vector3f(-paddingLeftRight, 0.064F, 0.51F), Axis.YP.rotationDegrees(0), ChatFormatting.WHITE.getColor()); // Front
        drawText(matrixStack, bufferIn, timeRate, new Vector3f(paddingLeftRight, 0.064F, -0.51F), Axis.YP.rotationDegrees(180F), ChatFormatting.WHITE.getColor()); // Back
        drawText(matrixStack, bufferIn, timeRate, new Vector3f(0.51F, 0.064F, paddingLeftRight), Axis.YP.rotationDegrees(90F), ChatFormatting.WHITE.getColor()); // Right
        drawText(matrixStack, bufferIn, timeRate, new Vector3f(-0.51F, 0.064F, -paddingLeftRight), Axis.YP.rotationDegrees(-90F), ChatFormatting.WHITE.getColor()); // Left
        drawText(matrixStack, bufferIn, timeRate, new Vector3f(-paddingLeftRight, 0.51F, -0.064F), Axis.XP.rotationDegrees(90F),  ChatFormatting.WHITE.getColor()); // Top
        drawText(matrixStack, bufferIn, timeRate, new Vector3f(-paddingLeftRight, -0.51F, 0.064F), Axis.XP.rotationDegrees(-90F),  ChatFormatting.WHITE.getColor()); // Bottom

        // Show how long it has left in seconds
        String remainiingTime = entity.getRemainingTime() / 20 + "s";
        drawText(matrixStack, bufferIn, remainiingTime, new Vector3f(-paddingLeftRight, 0.264F, 0.51F), Axis.YP.rotationDegrees(0), ChatFormatting.WHITE.getColor()); // Front
        drawText(matrixStack, bufferIn, remainiingTime, new Vector3f(paddingLeftRight, 0.264F, -0.51F), Axis.YP.rotationDegrees(180F), ChatFormatting.WHITE.getColor()); // Back
        drawText(matrixStack, bufferIn, remainiingTime, new Vector3f(0.51F, 0.264F, paddingLeftRight), Axis.YP.rotationDegrees(90F), ChatFormatting.WHITE.getColor()); // Right
        drawText(matrixStack, bufferIn, remainiingTime, new Vector3f(-0.51F, 0.264F, -paddingLeftRight), Axis.YP.rotationDegrees(-90F), ChatFormatting.WHITE.getColor()); // Left
        drawText(matrixStack, bufferIn, remainiingTime, new Vector3f(-paddingLeftRight, 0.51F, -0.264F), Axis.XP.rotationDegrees(90F),  ChatFormatting.WHITE.getColor()); // Top
        drawText(matrixStack, bufferIn, remainiingTime, new Vector3f(-paddingLeftRight, -0.51F, 0.264F), Axis.XP.rotationDegrees(-90F),  ChatFormatting.WHITE.getColor()); // Bottom
    }

    @Override
    public ResourceLocation getTextureLocation(TimeAcceleratorEntity entity) {
        return new ResourceLocation("");
    }

    private void drawText(PoseStack matrixStack, MultiBufferSource source, String text, Vector3f translateVector, Quaternionf rotate, int color) {
        matrixStack.pushPose();
        matrixStack.translate(translateVector.x(), translateVector.y(), translateVector.z());
        matrixStack.scale(0.02F, -0.02F, 0.02F);
        matrixStack.mulPose(rotate);
        getFont().drawInBatch(
                text,
                0,
                0,
                -1,
                false,
                matrixStack.last().pose(),
                source,
                Font.DisplayMode.NORMAL,
                0,
                color
        );
        matrixStack.popPose();
    }
}
