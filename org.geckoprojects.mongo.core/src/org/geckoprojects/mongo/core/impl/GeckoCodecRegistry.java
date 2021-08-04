package org.geckoprojects.mongo.core.impl;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClientSettings;

public class GeckoCodecRegistry implements CodecRegistry {


	CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry());
	@Override
	public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
		return codecRegistry.get(clazz, registry);

	}

	@Override
	public <T> Codec<T> get(Class<T> clazz) {
		return codecRegistry.get(clazz);
	}

}
