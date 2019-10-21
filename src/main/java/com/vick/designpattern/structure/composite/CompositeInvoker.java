package com.vick.designpattern.structure.composite;

public class CompositeInvoker {
    public static void main(String[] args) {
        Directory rootDir = new Directory("root");
        Directory userDir = new Directory("user");
        Directory binDir = new Directory("bin");
        Directory tmpDir = new Directory("tmp");

        rootDir.add(userDir);
        rootDir.add(binDir);
        rootDir.add(tmpDir);

        userDir.add(new File("Vick"));
        rootDir.pringlList("——");
    }
}
