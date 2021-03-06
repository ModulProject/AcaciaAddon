package fr.modulproject.acaciaddon.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.modulproject.acaciaddon.Main;
import fr.modulproject.acaciaddon.container.AcaciaEnricherContainer;
import fr.modulproject.acaciaddon.tileentity.AcaciaEnricherTileEntity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Created by Corentin on 08/03/2021 at 21:17
 */

@OnlyIn(Dist.CLIENT)
public class AcaciaEnricherScreen extends ContainerScreen<AcaciaEnricherContainer> {

    public static final ResourceLocation ACACIA_ENRICHER_GUI_TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/container/acacia_enricher_container.png");

    public AcaciaEnricherScreen(AcaciaEnricherContainer container, PlayerInventory inventory, ITextComponent textComponent) {
        super(container, inventory, textComponent);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.drawGuiContainerBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(ACACIA_ENRICHER_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);

        if(!this.container.getSlot(0).getHasStack())
            this.blit(matrixStack, i+52, j+31, 176, 0, 15, 15);

        //Between 0 and 56
        int p = 56 - (this.container.getProgress() * 56 / AcaciaEnricherTileEntity.ENRICH_TIME);
        this.blit(matrixStack, i+156, j+9+p, 192, p, 4, 56-p);
    }



}
