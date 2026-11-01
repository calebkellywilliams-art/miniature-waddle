package dev.example.anubisinspired;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public final class ClickGuiScreen extends Screen {
    private final List<GuiModule> modules = new ArrayList<>();
    private String category = "Combat";
    private String search = "";

    public ClickGuiScreen() {
        super(Component.literal("Anubis-inspired Click GUI"));
        modules.add(new GuiModule("Module list", "Visuals", "A sample visual toggle"));
        modules.add(new GuiModule("Coordinates", "HUD", "Display coordinates"));
        modules.add(new GuiModule("Keystrokes", "HUD", "Display key presses"));
        modules.add(new GuiModule("Fullbright", "Visuals", "Client-side brightness option"));
        modules.add(new GuiModule("Menu blur", "Visuals", "Decorative menu effect"));
        modules.add(new GuiModule("Auto-sprint", "Quality of Life", "Convenience toggle; behavior not implemented"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        int w = width;
        int h = height;
        int panelW = Math.min(720, w - 32);
        int panelH = Math.min(430, h - 32);
        int x = (w - panelW) / 2;
        int y = (h - panelH) / 2;

        g.fill(0, 0, w, h, 0x99050912);
        g.fill(x, y, x + panelW, y + panelH, 0xF0121723);
        g.fill(x, y, x + 150, y + panelH, 0xFF0D111B);
        g.fill(x + 150, y, x + panelW, y + 54, 0xFF151C2A);
        g.drawString(font, "ANUBIS", x + 20, y + 18, 0xFF55A9FF, false);
        g.drawString(font, "CLIENT  /  MODULES", x + 170, y + 18, 0xFFEAF2FF, false);
        g.drawString(font, "Right Shift to toggle", x + panelW - 160, y + 18, 0xFF9AA8BD, false);

        String[] categories = {"Combat", "Movement", "Player", "Visuals", "HUD", "Quality of Life"};
        int cy = y + 76;
        for (String c : categories) {
            boolean selected = c.equals(category);
            if (selected) g.fill(x + 12, cy - 5, x + 138, cy + 19, 0xFF245C9A);
            g.drawString(font, c, x + 24, cy, selected ? 0xFFFFFFFF : 0xFF9AA8BD, false);
            cy += 32;
        }

        int contentX = x + 174;
        int contentY = y + 78;
        g.fill(contentX, contentY - 12, x + panelW - 20, contentY + 15, 0xFF0D121C);
        g.drawString(font, "Search modules…", contentX + 10, contentY - 3, 0xFF78869B, false);
        contentY += 30;

        for (GuiModule module : modules) {
            if (!module.category.equals(category)) continue;
            if (!search.isBlank() && !module.name.toLowerCase().contains(search.toLowerCase())) continue;
            g.fill(contentX, contentY, x + panelW - 20, contentY + 52, 0xFF1B2433);
            g.drawString(font, module.name, contentX + 12, contentY + 10, 0xFFEAF2FF, false);
            g.drawString(font, module.description, contentX + 12, contentY + 28, 0xFF8C9BB1, false);
            int tx = x + panelW - 64;
            g.fill(tx, contentY + 17, tx + 28, contentY + 31,
                    module.enabled ? 0xFF3C9BFF : 0xFF465164);
            g.fill(module.enabled ? tx + 16 : tx + 2, contentY + 19,
                    module.enabled ? tx + 26 : tx + 12, contentY + 29, 0xFFFFFFFF);
            contentY += 60;
        }

        g.drawString(font, "Prototype UI • toggles are placeholders", x + 18, y + panelH - 24, 0xFF65758D, false);
        super.render(g, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int panelW = Math.min(720, width - 32);
        int panelH = Math.min(430, height - 32);
        int x = (width - panelW) / 2;
        int y = (height - panelH) / 2;

        String[] categories = {"Combat", "Movement", "Player", "Visuals", "HUD", "Quality of Life"};
        int cy = y + 76;
        for (String c : categories) {
            if (mouseX >= x + 12 && mouseX <= x + 138 && mouseY >= cy - 5 && mouseY <= cy + 19) {
                category = c;
                return true;
            }
            cy += 32;
        }

        int contentX = x + 174;
        int contentY = y + 108;
        for (GuiModule module : modules) {
            if (!module.category.equals(category)) continue;
            if (!search.isBlank() && !module.name.toLowerCase().contains(search.toLowerCase())) continue;
            if (mouseX >= contentX && mouseX <= x + panelW - 20 &&
                    mouseY >= contentY && mouseY <= contentY + 52) {
                module.enabled = !module.enabled;
                return true;
            }
            contentY += 60;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private static final class GuiModule {
        final String name;
        final String category;
        final String description;
        boolean enabled;

        GuiModule(String name, String category, String description) {
            this.name = name;
            this.category = category;
            this.description = description;
        }
    }
}
