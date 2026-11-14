package pl.holovis;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.UUID;

/**
 * Kosa Nametag: neonowo fioletowe "K" po lewej stronie nicku graczy używających KosaVisuals
 * (nad głową i w TAB). Mod działa tylko po stronie klienta, więc klient nie ma jak sprawdzić cudzych modów:
 * "K" dostajesz Ty sam (widoczne dla Ciebie w TAB i w trzeciej osobie) oraz bot UwU_Lizaczek.
 */
public final class KosaTag {
    private KosaTag() {}

    public static final String MARK = "holovis_kosa_tag";

    /** Czy ten tekst już ma nasze "K" (żeby nie dodać go dwa razy). */
    public static boolean has(Text t) {
        if (t == null || t.getSiblings().isEmpty()) return false;
        return MARK.equals(t.getSiblings().get(0).getStyle().getInsertion());
    }

    public static boolean enabled() { return HoloConfig.get().kosaTag; }

    public static boolean isUser(UUID id) {
        if (!enabled() || id == null) return false;
        MinecraftClient mc = MinecraftClient.getInstance();
        return mc != null && mc.player != null && id.equals(mc.player.getUuid());
    }

    public static boolean isUser(PlayerEntity p) {
        if (!enabled() || p == null) return false;
        MinecraftClient mc = MinecraftClient.getInstance();
        return p == (mc == null ? null : mc.player) || GhostBot.isBot(p);
    }

    /** Kolorowe "K" + nick w oryginalnym kolorze (nick jest osobnym elementem, więc nie dziedziczy koloru K). */
    public static MutableText tag(Text name) {
        int rgb = HoloConfig.get().kosaTagColor.rgb();
        MutableText k = Text.literal("K").setStyle(Style.EMPTY.withColor(rgb).withBold(true).withInsertion(MARK));
        return Text.empty().append(k).append(Text.literal(" ")).append(name);
    }
}
