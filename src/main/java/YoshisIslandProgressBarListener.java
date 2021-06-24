import com.intellij.ide.plugins.DynamicPluginListener;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;

public class YoshisIslandProgressBarListener implements LafManagerListener, DynamicPluginListener {
    private static final String PROGRESS_BAR_UI_KEY = "ProgressBarUI";
    private static final String YOSHIS_ISLAND_PROGRESS_BAR_UI_IMPL_NAME = YoshisIslandProgressBarUi.class.getName();
    private volatile static Object previousProgressBar = null;

    public YoshisIslandProgressBarListener() {
        updateProgressBarUi();
    }

    @Override
    public void lookAndFeelChanged(@NotNull final LafManager lafManager) {
        updateProgressBarUi();
    }

    @Override
    public void pluginLoaded(@NotNull final IdeaPluginDescriptor pluginDescriptor) {
        updateProgressBarUi();
    }

    @Override
    public void beforePluginUnload(@NotNull final IdeaPluginDescriptor pluginDescriptor, final boolean isUpdate) {
        resetProgressBarUi();
    }

    private static void updateProgressBarUi() {
        final Object prev = UIManager.get(PROGRESS_BAR_UI_KEY);

        if (!Objects.equals(YOSHIS_ISLAND_PROGRESS_BAR_UI_IMPL_NAME, prev)) {
            previousProgressBar = prev;
        }

        UIManager.put(PROGRESS_BAR_UI_KEY, YOSHIS_ISLAND_PROGRESS_BAR_UI_IMPL_NAME);
        UIManager.getDefaults().put(YoshisIslandProgressBarUi.class.getName(), YoshisIslandProgressBarUi.class);
    }

    private static void resetProgressBarUi() {
        UIManager.put(PROGRESS_BAR_UI_KEY, previousProgressBar);
    }
}
