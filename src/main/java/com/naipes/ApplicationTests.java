package com.naipes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class ApplicationTests {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setAge(20);
        person.setName("Joe");
        System.out.println(person);

        System.out.println("\nSTRING METHODS\n");
        String serializedPerson = toString(person);
        System.out.println(serializedPerson);
        Person unserializedPerson = (Person) fromString(serializedPerson);
        System.out.println("Name: " + unserializedPerson.getName() + " Age: " + unserializedPerson.getAge());

        System.out.println("\nBYTE ARRAY METHODS\n");
        byte[] serializedPersonInBytes = toByteArray(person);
        System.out.println(serializedPersonInBytes);
        Person unserializedPersonInBytes = (Person) fromString(serializedPerson);
        System.out.println(
                "Name: " + unserializedPersonInBytes.getName() + " Age: " + unserializedPersonInBytes.getAge());

        System.out.println("\nANOTHER BYTES METHOD\n");
        simpleDynamicByteArray();
    }

    /**
     * Read the object from Base64 string.
     */
    private static Object fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    /**
     * Write the object to a Base64 string.
     */
    private static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    /**
     * Read the object from Base64 string.
     */
    private static Object fromByteArray(byte[] bytes) throws IOException,
            ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(bytes));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    /**
     * Write the object to a Base64 string.
     */
    private static byte[] toByteArray(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return baos.toByteArray();
    }

    /**
     * Write the object to a Base64 string.
     */
    private static void simpleDynamicByteArray() throws IOException {
        String data = "Hello World!";
        byte[] bytesData = data.getBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(bytesData);
        System.out.println("Before serialization: " + bytesData);
        String streamData = outputStream.toString();
        outputStream.close();
        System.out.println("From serialization: " + streamData);
    }

}
