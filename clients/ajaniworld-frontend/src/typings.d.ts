/* SystemJS module definition */
declare var module: NodeModule;
interface NodeModule {
  id: string;
}

declare var process: Process;

interface Process {
  env: Env
}

interface Env {
  AJANIWORLD_API_BASEURL: string
}

interface GlovalEnvironment {
  process: Process
}
