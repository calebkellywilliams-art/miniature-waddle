# Anubis-inspired Fabric Click GUI (prototype)

This is a source scaffold for a Minecraft Java client-side Fabric mod, targeting 1.21.11.
It recreates the broad layout of the supplied reference: dark panels, blue accent, category rail,
module cards, and a Right Shift menu key. It is **not** an exact copy of Anubis and does not
implement cheat modules. The toggles are UI placeholders.

## Build

1. Install JDK 21 and a compatible Gradle installation (or add the Gradle wrapper).
2. In `gradle.properties`, replace `REPLACE_WITH...` with current versions compatible with Minecraft 1.21.11 from Fabric's official version pages.
3. Run `gradle build`.
4. The mod jar will be in `build/libs/`.

The starter uses Mojang official mappings. Fabric/Minecraft APIs can change between point releases;
if your exact 1.21.11 setup reports a compile error, adjust the affected mapping/API names to the
versions selected above.

## Controls

- Right Shift opens the menu.
- Click a category to change the visible group.
- Click a module card to toggle its placeholder state.
- Escape closes the screen.

## Scope

The menu is a visual prototype. It intentionally does not include combat cheats, anti-cheat bypasses,
or unfair multiplayer automation.
