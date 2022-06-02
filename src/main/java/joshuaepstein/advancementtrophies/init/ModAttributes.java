package joshuaepstein.advancementtrophies.init;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.attributes.MainAttribute;
import joshuaepstein.advancementtrophies.attributes.StringAttribute;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModAttributes {

    public static Map<ResourceLocation, MainAttribute<?, ?>> REGISTRY = new HashMap();

    public static MainAttribute<String, StringAttribute> ADVANCEMENT_NAME;
    public static MainAttribute<String, StringAttribute> ADVANCEMENT_ITEM;

    public static void register(RegistryEvent.Register<Attribute> event){
        ADVANCEMENT_NAME = register(Main.id("advancement_name"), StringAttribute::new);
        ADVANCEMENT_ITEM = register(Main.id("advancement_item"), StringAttribute::new);
    }

    private static Attribute register(IForgeRegistry<Attribute> registry, String name, Attribute attribute){
        registry.register(attribute.setRegistryName(Main.id(name)));
        return attribute;
    }

    @SafeVarargs
    private static <T, I extends MainAttribute.Instance<T>> MainAttribute<T, I> register(ResourceLocation id, Supplier<I> instance, MainAttribute<T, I>... modifiers){
        MainAttribute<T, I> attribute = new MainAttribute<>(id, instance, modifiers);
        REGISTRY.put(id, attribute);
        return attribute;
    }

}
