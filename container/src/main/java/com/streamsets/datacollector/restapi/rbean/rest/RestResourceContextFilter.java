/*
 * Copyright 2020 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.datacollector.restapi.rbean.rest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RestResourceContextFilter implements Filter {

  private static final ThreadLocal<Boolean> REST_RESOURCE_TL = ThreadLocal.withInitial(() -> Boolean.FALSE);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain
  ) throws IOException, ServletException {
    try {
      REST_RESOURCE_TL.remove();
      filterChain.doFilter(servletRequest, servletResponse);
    } finally {
      REST_RESOURCE_TL.remove();
    }
  }

  @Override
  public void destroy() {

  }

  static void setRestResource() {
    REST_RESOURCE_TL.set(Boolean.TRUE);
  }

  static boolean isRestResource() {
    return REST_RESOURCE_TL.get();
  }

}
