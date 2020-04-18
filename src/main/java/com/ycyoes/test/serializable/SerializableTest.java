package com.ycyoes.test.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;

import org.apache.ibatis.cache.CacheException;
import org.apache.ibatis.io.Resources;

public class SerializableTest {

	  private byte[] serialize(Serializable value) {
	    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
	         ObjectOutputStream oos = new ObjectOutputStream(bos)) {
	      oos.writeObject(value);
	      oos.flush();
	      return bos.toByteArray();
	    } catch (Exception e) {
	      throw new CacheException("Error serializing object.  Cause: " + e, e);
	    }
	  }

	  private Serializable deserialize(byte[] value) {
	    Serializable result;
	    try (ByteArrayInputStream bis = new ByteArrayInputStream(value);
	         ObjectInputStream ois = new CustomObjectInputStream(bis)) {
	      result = (Serializable) ois.readObject();
	    } catch (Exception e) {
	      throw new CacheException("Error deserializing object.  Cause: " + e, e);
	    }
	    return result;
	  }
	  
	  public static class CustomObjectInputStream extends ObjectInputStream {

		    public CustomObjectInputStream(InputStream in) throws IOException {
		      super(in);
		    }

		    @Override
		    protected Class<?> resolveClass(ObjectStreamClass desc) throws ClassNotFoundException {
		      return Resources.classForName(desc.getName());
		    }

		  }
}
