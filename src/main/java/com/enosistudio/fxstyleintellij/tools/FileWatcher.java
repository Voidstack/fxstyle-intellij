package com.enosistudio.fxstyleintellij.tools;

import java.io.IOException;
import java.nio.file.*;

/**
 * Utilitaire pour surveiller les modifications d'un fichier et exécuter une action en conséquence.
 */
public class FileWatcher {
    public static void watchFile(Path filePath, Runnable onChange) throws IOException {
        Path dir = filePath.getParent();
        String fileName = filePath.getFileName().toString();

        WatchService watchService = FileSystems.getDefault().newWatchService();
        dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    WatchKey key = watchService.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        Path changed = (Path) event.context();
                        if (changed.getFileName().toString().equals(fileName)) {
                            onChange.run();
                        }
                    }
                    key.reset();
                }
            } catch (InterruptedException ignored) {
            }
        }, "FileWatcherThread");

        thread.setDaemon(true);
        thread.start();
    }
}
