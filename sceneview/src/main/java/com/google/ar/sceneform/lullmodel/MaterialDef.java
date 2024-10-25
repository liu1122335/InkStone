// automatically generated by the FlatBuffers compiler, do not modify

package com.google.ar.sceneform.lullmodel;

import java.nio.*;

import com.google.flatbuffers2.*;
import com.google.flatbuffers2.FlatBufferBuilder;
import com.google.flatbuffers2.Table;

@SuppressWarnings("unused")
/**
 * A description of the Material to be used by the RenderSystem when drawing
 * an Entity.  A Material describes the "look" applied to a single surface
 * of a mesh.
 */
public final class MaterialDef extends Table {
  public static MaterialDef getRootAsMaterialDef(ByteBuffer _bb) { return getRootAsMaterialDef(_bb, new MaterialDef()); }
  public static MaterialDef getRootAsMaterialDef(ByteBuffer _bb, MaterialDef obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public MaterialDef __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * The name of the material.
   */
  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer nameInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  /**
   * A dictionary of all material properties extracted from the source file.
   * These properties are interpretted by the RenderSystem to create the
   * appropriate Material.
   */
  public VariantMapDef properties() { return properties(new VariantMapDef()); }
  public VariantMapDef properties(VariantMapDef obj) { int o = __offset(6); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  /**
   * The list of textures associated with the Material.
   */
  public MaterialTextureDef textures(int j) { return textures(new MaterialTextureDef(), j); }
  public MaterialTextureDef textures(MaterialTextureDef obj, int j) { int o = __offset(8); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int texturesLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }

  public static int createMaterialDef(FlatBufferBuilder builder,
                                      int nameOffset,
                                      int propertiesOffset,
                                      int texturesOffset) {
    builder.startObject(3);
    MaterialDef.addTextures(builder, texturesOffset);
    MaterialDef.addProperties(builder, propertiesOffset);
    MaterialDef.addName(builder, nameOffset);
    return MaterialDef.endMaterialDef(builder);
  }

  public static void startMaterialDef(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addProperties(FlatBufferBuilder builder, int propertiesOffset) { builder.addOffset(1, propertiesOffset, 0); }
  public static void addTextures(FlatBufferBuilder builder, int texturesOffset) { builder.addOffset(2, texturesOffset, 0); }
  public static int createTexturesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startTexturesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endMaterialDef(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

