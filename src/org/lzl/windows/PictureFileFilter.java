/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.windows;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author liangzl2
 */


public final class PictureFileFilter extends FileFilter {

    private final String extension;

    private final String description;

    public PictureFileFilter(String extension, String description) {
        super();
        this.extension = extension;
        this.description = description;
    }

    @Override
    public boolean accept(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                return true;
            }
            String extension1 = getExtension(f);
            if (extension1 != null && extension1.equalsIgnoreCase(this.extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private String getExtension(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
        }
        return null;
    }

}
