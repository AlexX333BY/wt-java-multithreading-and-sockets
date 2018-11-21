package by.bsuir.kaziukovich.archive.server.data.impl;

import by.bsuir.kaziukovich.archive.domain.StudentRecord;
import by.bsuir.kaziukovich.archive.server.data.RecordReadWriteException;
import by.bsuir.kaziukovich.archive.server.data.UserRecordReaderWriter;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentRecordXmlFileReaderWriter implements UserRecordReaderWriter<StudentRecord> {
    @Override
    public List<StudentRecord> readFrom(String path) throws RecordReadWriteException {
        if (path == null) {
            throw new IllegalArgumentException("Path shouldn't be null");
        }

        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(path))) {
            return (List<StudentRecord>) decoder.readObject();
        } catch (FileNotFoundException e) {
            throw new RecordReadWriteException("File with path " + path + " was not found", e);
        } catch (ClassCastException e) {
            throw new RecordReadWriteException("File with path " + path + " doesn't contain student records", e);
        }
    }

    @Override
    public void writeTo(List<StudentRecord> records, String path) throws RecordReadWriteException {
        if ((records == null) || (path == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(path))) {
            List<SerializableStudentRecord> toWrite = new ArrayList<>();
            RecordReadWriteException aggregatedIllegalArgumentExceptions
                    = new RecordReadWriteException("Cannot cast some records");

            for (StudentRecord record : records) {
                try {
                    toWrite.add(new SerializableStudentRecord(record));
                } catch (IllegalArgumentException e) {
                    aggregatedIllegalArgumentExceptions.addSuppressed(e);
                }
            }
            encoder.writeObject(toWrite);

            if (aggregatedIllegalArgumentExceptions.getSuppressed().length != 0) {
                throw aggregatedIllegalArgumentExceptions;
            }
        } catch (FileNotFoundException e) {
            throw new RecordReadWriteException("Cannot find file " + path, e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
