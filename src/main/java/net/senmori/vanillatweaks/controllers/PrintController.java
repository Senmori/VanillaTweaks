package net.senmori.vanillatweaks.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.util.Consumer;

public class PrintController extends TweakController {

    boolean suppressOut, suppressError;

    public PrintController(VanillaTweaks plugin) {
        super(plugin);
        suppressOut = suppressError = false; // default to false;
        suppressOut = getPlugin().getTweakConfig().canSuppressOut();
        suppressError = getPlugin().getTweakConfig().canSuppressErr();

        suppressOutput();
    }

    private void suppressOutput() {
        getPlugin().getLogger().info("Suppressing STDOUT=" + suppressOut + ", STDERR=" + suppressError);
        if(suppressOut || suppressError) {
            getPlugin().getLogger().info("VanillaTweaks print suppression is enabled. Important info might be missing.");
        }

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
        consumer.accept(oppressedStream);
    }
}
