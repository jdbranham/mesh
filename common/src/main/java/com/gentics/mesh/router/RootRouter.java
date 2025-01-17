package com.gentics.mesh.router;

import static com.gentics.mesh.handler.VersionHandler.API_MOUNTPOINT;

import com.gentics.mesh.etc.config.MeshOptions;
import com.gentics.mesh.router.route.DefaultNotFoundHandler;
import com.gentics.mesh.router.route.FailureHandler;
import com.gentics.mesh.router.route.PoweredByHandler;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;

/**
 * The root router is the top level router of the routing stack.
 */
public class RootRouter {

	private final APIRouter apiRouter;

	private final CustomRouter customRouter;

	private final Router router;

	private RouterStorage storage;

	private Vertx vertx;

	public RootRouter(Vertx vertx, RouterStorage storage, MeshOptions options) {
		this.storage = storage;
		this.vertx = vertx;
		this.router = Router.router(vertx);
		// Root handlersA
		router.route().handler(LoggerHandler.create(LoggerFormat.SHORT));
		// TODO add a dedicated error for api router that informs about
		// APPLICATION_JSON requirements. This may not be true for other
		// routes (eg. custom
		// routes)
		router.route().last().handler(DefaultNotFoundHandler.create());
		router.route().failureHandler(FailureHandler.create());
		router.route().handler(PoweredByHandler.create());
		router.route(API_MOUNTPOINT).handler(storage.versionHandler);

		this.apiRouter = new APIRouter(vertx, this, options);
		this.customRouter = new CustomRouter(vertx, this);
	}

	public Router getRouter() {
		return router;
	}

	public APIRouter apiRouter() {
		return apiRouter;
	}

	public CustomRouter customRouter() {
		return customRouter;
	}

	public Vertx getVertx() {
		return vertx;
	}

	public RouterStorage getStorage() {
		return storage;
	}
	

}
