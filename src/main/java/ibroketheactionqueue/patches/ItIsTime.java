package ibroketheactionqueue.patches;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireRawPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.clapper.util.classutil.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ItIsTime
{
    public static class ToParty
    {
        @SpirePatch(clz = GameActionManager.class, method = "addToTop")
        public static class ImATop
        {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(GameActionManager __instance, AbstractGameAction a)
            {
                if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
                    __instance.actions.add(a);
                return SpireReturn.Return();
            }
        }

        @SpirePatch(clz = GameActionManager.class, method = "addToBottom")
        public static class ImABottom
        {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(GameActionManager __instance, AbstractGameAction a)
            {
                if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
                    __instance.actions.add(0, a);
                return SpireReturn.Return();
            }
        }
    }
}