package net.senmori.vanillatweaks.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Consumer;

public class PrintController extends TweakController {

    boolean suppressOut, suppressError;

    public PrintController(VanillaTweaks plugin) {
        super(plugin);
        suppressOut = suppressError = false; // default to false;
        suppressOut = getPlugin().getTweakConfig().getSuppressOut();
        suppressError = getPlugin().getTweakConfig().getSuppressErr();
    }

    private void suppressOutput() {
        getPlugin().getLogger().warning("VanillaTweaks print suppression is enabled. Important info might be missing.");
        getPlugin().getLogger().warning("Suppressing STDOUT=" + suppressOut + ", STDERR=" + suppressError);

        if(suppressOut)
            suppress(System::setOut);
        if(suppressError)
            suppress(System::setErr);
    }

    private void suppress(Consumer<PrintStream> consumer) {
        PrintStream oppressedStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
               // no output
            }
        });
    }
}