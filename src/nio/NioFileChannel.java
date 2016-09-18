package nio;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class NioFileChannel {

	public static void main(String[] args) throws Exception{
		FileInputStream file = new FileInputStream("resource/Graph/tinyG.txt");
		FileChannel inChannel = file.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buffer);
		while(bytesRead !=-1){
			//System.out.println("Read" + bytesRead);
			
			buffer.flip();
			while(buffer.hasRemaining()){
				System.out.print((char) buffer.get());
			}
			
			buffer.clear();
			bytesRead = inChannel.read(buffer);
		}
		inChannel.close();
		file.close();
	}

}

class ResourceFactory {     
    private static class ResourceHolder {     
        public static Resource resource = new Resource();     
    }     
    
    public static Resource getResource() {     
        return ResourceFactory.ResourceHolder.resource;     
    }     
    
     static class Resource {     
     }     
 }

class Singleton{
	private static class SingletonHolder{
		public static Singleton instance = new Singleton();
	}
	
	private Singleton(){
		
	}
	
	public static Singleton getInstance(){
		return Singleton.SingletonHolder.instance;
	}
	
}


