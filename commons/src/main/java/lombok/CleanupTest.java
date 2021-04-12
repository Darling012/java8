package lombok;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Cleanup注解 资源关闭
 * 你可以使用@Cleanup确保在代码执行后，在退出当前作用域之前自动清除给定资源。你可以通过使用@Cleanup来注释任何
 * 局部变量 声明
 *
 * 它会在代码块的结尾处自动执行 in.close()方法，lombok 能确保此方法在try…finally…块内执行
 * 如果你要清理的对象类型没有 close() 方法，而是其他一些无参数方法，则可以指定此方法的名称，如下所示：
 * @Cleanup("dispose") org.eclipse.swt.widgets.CoolBar bar = new CoolBar(parent, 0);
 *
 *
 */
public class CleanupTest {

    public void copyFile(String in, String out)
            throws Exception {
        @Cleanup FileInputStream fileInputStream =
                new FileInputStream(in);
        @Cleanup FileOutputStream fileOutputStream =
                new FileOutputStream(out);
        int r;
        while ((r = fileInputStream.read()) != -1) {
            fileOutputStream.write(r);
        }
    }

}
